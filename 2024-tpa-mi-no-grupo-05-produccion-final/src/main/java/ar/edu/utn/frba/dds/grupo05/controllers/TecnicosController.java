package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaVulnerableInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.TecnicoInputDTO;
import ar.edu.utn.frba.dds.grupo05.repositories.ubicacion.RepositorioLocalidad;
import ar.edu.utn.frba.dds.grupo05.services.localidadService.imp.ILocalidadService;
import ar.edu.utn.frba.dds.grupo05.services.localidadService.imp.LocalidadService;
import ar.edu.utn.frba.dds.grupo05.services.tecnicoservices.ITecnicoService;
import ar.edu.utn.frba.dds.grupo05.controllers.RoleBasedController;
import ar.edu.utn.frba.dds.grupo05.converters.TipoDocumentoConverter;
import ar.edu.utn.frba.dds.grupo05.converters.TipoContactoConverter;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TecnicosController extends RoleBasedController{

  private ITecnicoService tecnicoService;
  private LocalidadService localidadService = new LocalidadService(new RepositorioLocalidad());

  public TecnicosController(ITecnicoService tecnicoService, LocalidadService localidadService){
    this.tecnicoService = tecnicoService;
  }

  public void save(Context context) {

    /*if (!esPersonaJuridica(context)) {

      context.status(403);
      context.render("views/estados/error-permiso-denegado.hbs");
      return;
    }*/

    String nombre = context.formParam("nombre");
    String apellido = context.formParam("apellido");
    String usuario = context.formParam("usuario");
    String contrasenia = context.formParam("contrasenia");
    String tipoDocumento = context.formParam("tipoDocumento");
    String cuil = context.formParam("cuil");
    String tipoContacto = context.formParam("medioDeContacto.tipo");
    String valorContacto = context.formParam("medioDeContacto.valor");
    String localidadId = context.formParam("localidad");
    String latitud = context.formParam("latitud");
    String longitud = context.formParam("longitud");


    TecnicoInputDTO tecnicoInputDTO = TecnicoInputDTO
            .builder()
            .nombre(nombre)
            .apellido(apellido)
            .usuario(usuario)
            .contrasenia(contrasenia)
            .tipoDocumento(tipoDocumento)
            .cuil(cuil)
            .tipoContacto(tipoContacto)
            .valorContacto(valorContacto)
            .localidad(localidadId)
            .latitud(latitud)
            .longitud(longitud)
            .build();

    //Usuario usuario = context.sessionAttribute("usuario"); despues lo vemos esto
    this.tecnicoService.darDeAlta(tecnicoInputDTO);

    Map<String, Object> model = new HashMap<>();
    model.put("nombre", tecnicoInputDTO.getNombre());
    model.put("apellido", tecnicoInputDTO.getApellido());
    model.put("tipoDocumento", TipoDocumentoConverter.convertir(tecnicoInputDTO.getTipoDocumento()));
    model.put("cuil", tecnicoInputDTO.getCuil());
    model.put("tipoContacto", TipoContactoConverter.convertir(tecnicoInputDTO.getTipoContacto()));
    model.put("valorContacto", tecnicoInputDTO.getValorContacto());
    model.put("localidad", tecnicoInputDTO.getLocalidad());

    context.render("views/colaboraciones/formularios/estados/registro-tecnico-exitoso.hbs", model);
  }

  public void formulario(Context context) {
    Usuario user = context.sessionAttribute("usuario");
    Map<String, Object> model = this.getModel(context);

    //model.put("localidades", this.localidadService.obtenerModelos());

    context.render("views/tecnicos/registrar-tecnico.hbs", model);
  }

  public void registroExito(Context context) {
    String titulo = "Registro exitoso";
    String mensaje = "Registro como persona vulnerable realizado con éxito. ¡Gracias por registrarte!";
    context.render("views/estados/success.hbs", Map.of("titulo", titulo, "mensaje", mensaje));
  }

}
