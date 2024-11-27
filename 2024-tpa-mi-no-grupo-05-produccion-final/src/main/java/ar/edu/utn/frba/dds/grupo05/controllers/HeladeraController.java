package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.EstadoHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.Incidente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.VisitaHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.ModeloHeladeraDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.VisitaHeladeraDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.DistribucionViandaNotFoundException;
import ar.edu.utn.frba.dds.grupo05.services.VisitaHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.fallatecnica.IncidenteDTO;
import ar.edu.utn.frba.dds.grupo05.services.fallatecnica.IncidenteService;
import ar.edu.utn.frba.dds.grupo05.services.heladera.IModeloHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.heladera.IHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.heladera.ModeloHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.recomendadorpuntosgeograficos.RecomendadorPuntosGeograficos;
import ar.edu.utn.frba.dds.grupo05.services.tecnicoservices.impl.TecnicoService;
import com.fasterxml.jackson.databind.jsontype.impl.MinimalClassNameIdResolver;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class HeladeraController extends RoleBasedController implements ICrudViewsHandler{

    VisitaHeladeraService visitaHeladeraService;
    IncidenteService incidenteService;
    IHeladeraService heladeraService;
    ModeloHeladeraService modeloService;
    TecnicoService tecnicoService;
    RecomendadorPuntosGeograficos recomendadorPuntosGeograficos;
    VisitaHeladeraDTO globalDTO;

  public HeladeraController(IHeladeraService heladeraService,
                            ModeloHeladeraService modeloServices,
                            IncidenteService incidenteServices,
                            VisitaHeladeraService visitaHeladeraServices,
                            TecnicoService tecnicoServices) {
      this.modeloService = modeloServices;
      this.heladeraService = heladeraService;
      this.incidenteService = incidenteServices;
      this.visitaHeladeraService = visitaHeladeraServices;
      this.tecnicoService = tecnicoServices;
  }

  @Override
  public void index(Context context) {
    Map<String, Object> model = this.getModel(context);
    model.put("heladeras", this.heladeraService.obtenerTodas());
    context.render("views/heladeras/mapa-heladeras.hbs", model);
  }

  public void indexIncidentes(Context context) {
      Map<String, Object> model = this.getModel(context);
      model.put("incidentes", this.incidenteService.obtenerTodos());
      context.render("views/tecnicos/mapa-incidentes.hbs", model);
  }

    public void indexVisitaHealdera(Context context) {
        Map<String, Object> model = this.getModel(context);
        model.put("visitas", this.visitaHeladeraService.obtenerVisitas());
        context.render("views/tecnicos/vistas-heladeras.hbs", model);
    }

  public void createIncidente(Context context) throws IOException {

      Long idIncidente  = Long.valueOf(Objects.requireNonNull(context.formParam("incidente.id")));

      Incidente nuevoIncidente = this.incidenteService.buscarPorId(idIncidente).orElseThrow(()
              -> new RuntimeException("Incidente no encontrada con ID " + idIncidente));

      String fecha  = context.formParam("fechaVisita");
      String comentario = context.formParam("comentario");
      UploadedFile imagen = context.uploadedFile("imagen");
      String fileName = null;

      if(imagen != null) {
          fileName = imagen.filename();
          InputStream content = imagen.content();
          String urlImagen = "imagenes/" + fileName;
          Files.copy(content, Paths.get(urlImagen), StandardCopyOption.REPLACE_EXISTING);
      }
      String resuelto = context.formParam("resuelto") != null ? "SOLUCIONADA" : "PENDIENTE";
      Heladera heladera = this.heladeraService.obtenerPorId(nuevoIncidente.getHeladera().getId());
      if ("SOLUCIONADA".equals(resuelto)) {
          heladera.setEstadoHeladera(EstadoHeladera.ACTIVA);
      }
      Optional<Tecnico> tecnico = this.tecnicoService.tecnicosRepository.buscarPorId(nuevoIncidente.getTecnicoAsignado().getId());

      Tecnico tecnico1 = null;
      if(!tecnico.isEmpty()){
          tecnico1 = tecnico.get();
      }
      assert tecnico1 != null;

      VisitaHeladeraDTO visitaHeladeraDTO = VisitaHeladeraDTO
              .builder()
              .id_incidente(String.valueOf(idIncidente))
              .fecha(fecha)
              .comentario(comentario)
              .path(fileName)
              .resuelto(resuelto)
              .heladera(heladera)
              .tecnico(tecnico1)
              .build();

      Usuario usuario = context.sessionAttribute("usuario");
      this.visitaHeladeraService.create(visitaHeladeraDTO);


      Map<String, Object> model = new HashMap<>();
      model.put("id_heladera", heladera.getId());
      model.put("id_tecnico", tecnico1.getId());
      model.put("fecha", fecha);
      model.put("comentario", comentario);
      model.put("resuelto", resuelto);

      context.render("views/registros/registro-incidente-solucionado.hbs", model);
  }

  @Override
  public void show(Context context) {

  }

    public void create(Context context) {
      //Esto tiene que dejarse dps
    /*if (!esPersonaJuridica(context)) {

      context.status(403);
      context.render("views/estados/error-permiso-denegado.hbs");
      return;
    }*/

        String name = context.formParam("fridgeName");
        String capacity = context.formParam("fridgeCapacity");
        Long modelo = Long.valueOf(context.formParam("fridgeModel"));
        String latitude = context.formParam("fridgeLatitude");
        String longitude = context.formParam("fridgeLongitude");
        String direccion = context.formParam("direccion");
        String descripcion = context.formParam("description");
        String nombreLoc = context.formParam("localidad");

        assert capacity != null;
        Integer capacidadInt = Integer.parseInt(capacity.trim());

        HeladeraOutputDTO heladeraOutputDTO = HeladeraOutputDTO
                .builder()
                .name(name)
                .capacity(capacidadInt)
                .model(modelo)
                .latitude(latitude)
                .longitude(longitude)
                .direccion(direccion)
                .descripcion(descripcion)
                .localidad(nombreLoc)
                .build();

        Usuario usuario = context.sessionAttribute("usuario");
        this.heladeraService.create(usuario, heladeraOutputDTO);

        Map<String, Object> model = new HashMap<>();
        model.put("name", heladeraOutputDTO.getName());
        model.put("capacity", heladeraOutputDTO.getCapacity());
        model.put("modelo", heladeraOutputDTO.getModel());
        model.put("latitude", heladeraOutputDTO.getLatitude());
        model.put("longitude", heladeraOutputDTO.getLongitude());
        model.put("direccion", heladeraOutputDTO.getDireccion());
        model.put("descripcion", heladeraOutputDTO.getDescripcion());

        context.render("views/colaboraciones/formularios/estados/registro-heladera-exitosa.hbs", model);
    }

    @Override
    public void save(Context context) {

    }

    @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }

  public void formulario(Context context) {
      Usuario user = context.sessionAttribute("usuario");
      Map<String, Object> model = this.getModel(context);

      model.put("heladeras", this.heladeraService.obtenerTodas());
      model.put("fridgeModel", this.modeloService.obtenerModelos());

      context.render("views/heladeras/creacion-heladeras.hbs", model);
  }

  public void registroExito(Context context) {
    String titulo = "Registro exitoso";
    String mensaje = "Registro de heladera realizado con éxito. ¡Gracias por registrarte!";
    context.render("views/estados/success.hbs", Map.of("titulo", titulo, "mensaje", mensaje));
  }



}
