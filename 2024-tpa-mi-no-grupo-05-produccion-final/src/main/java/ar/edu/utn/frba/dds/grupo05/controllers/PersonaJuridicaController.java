package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.TipoContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.Rubro;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoJuridico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import ar.edu.utn.frba.dds.grupo05.dtos.UsuarioInputDTO;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorMensaje;
import ar.edu.utn.frba.dds.grupo05.factories.EnviadorMensajeFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.exceptions.RubroNotFoundException;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorMensaje;
import ar.edu.utn.frba.dds.grupo05.factories.EnviadorMensajeFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioRubro;
import ar.edu.utn.frba.dds.grupo05.services.usuario.IUsuarioService;
import ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.validadores.Validador;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonaJuridicaController implements ICrudViewsHandler {
    private RepositorioPersonaJuridica repositorioPersonaJuridica;

    private RepositorioRubro repositorioRubro;
    private IUsuarioService usuarioService;

    public PersonaJuridicaController(RepositorioPersonaJuridica repositorioDePersonaJuridica,RepositorioRubro repositorioRubro,
                                     IUsuarioService usuarioService){
        this.repositorioPersonaJuridica = repositorioDePersonaJuridica;
        this.usuarioService = usuarioService;
        this.repositorioRubro= repositorioRubro;
    }

    @Override
    public void index(Context context) {
      List<PersonaJuridica> personasJuridicas = this.repositorioPersonaJuridica.buscarPersonaJuridica();

      Map<String, Object> model = new HashMap<>();
      model.put("personasJuridicas", personasJuridicas);
      model.put("titulo", "Listado de productos");
    }

  @Override
  public void show(Context context) {}

  @Override
  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();

    // Añadir datos estáticos al modelo para el formulario
    model.put("titulo", "Crear Nueva Persona Jurídica");

    model.put("formasDeColaboracion", List.of("DonacionDinero", "DonacionVianda", "DistribucionViandas"));

    model.put("tiposJuridicos", List.of("Gubernamental", "ONG", "Empresa", "Institucion"));

    context.render("views/registros/registro-persona-juridica.hbs", model);
  }

  @Override
  public void save(Context context) {

    PersonaJuridica nuevaPersonaJuridica = new PersonaJuridica();
    nuevaPersonaJuridica.setRazonSocial(context.formParam("razonSocial"));

    String tipoJuridicoStr = context.formParam("tipoJuridico");
    nuevaPersonaJuridica.setTipoJuridico(TipoJuridico.valueOf(tipoJuridicoStr.toUpperCase()));

    // Crear y asignar Rubro

    String rubroStr = context.formParam("rubro");
    Rubro rubro = repositorioRubro.buscarPorNombre(rubroStr)
        .orElseGet(() -> {
          Rubro nuevoRubro = new Rubro();
          nuevoRubro.setNombre(rubroStr);
          repositorioRubro.guardar(nuevoRubro);
          return nuevoRubro;
        });
    nuevaPersonaJuridica.setRubro(rubro);

    // Lógica para agregar medios de contacto
    List<MedioDeContacto> mediosDeContacto = new ArrayList<>();
    List<String> contactos = context.formParams("contacto");
    List<String> tiposContacto = context.formParams("tipoContacto");
    List<String> enviadoresMensaje = context.formParams("enviadorMensaje");


    for (int i = 0; i < contactos.size(); i++) {
      String contacto = contactos.get(i);
      TipoContacto tipoContacto = TipoContacto.valueOf(tiposContacto.get(i).toUpperCase());
      EnviadorMensaje enviador = EnviadorMensajeFactory.crearEnviador(enviadoresMensaje.get(i));  // Asumiendo el uso de un Factory

      // Crear el objeto MedioDeContacto y agregarlo a la lista
      MedioDeContacto medio = new MedioDeContacto();
      medio.setTipo(tipoContacto);
      medio.setContacto(contacto);
      medio.setEnviador(enviador);
      mediosDeContacto.add(medio);
    }

    nuevaPersonaJuridica.setMediosDeContacto(mediosDeContacto);

    UsuarioInputDTO usuarioDTO = UsuarioInputDTO.builder()
            .nombreDeUsuario(context.formParam("usuario"))
            .contrasenia(context.formParam("contrasena"))
            .rol(TipoRol.PERSONA_JURIDICA)
            .build();

    nuevaPersonaJuridica.setUsuario(this.usuarioService.darAltaUsuario(usuarioDTO));
    this.repositorioPersonaJuridica.guardarPersonaJuridica(nuevaPersonaJuridica);



    this.registroExito(context);
  }




  @Override
  public void edit(Context context) {

    Optional<PersonaJuridica> posiblePersonaJuridica = this.repositorioPersonaJuridica.buscarPorId(Long.valueOf(context.pathParam("id")));

    if(posiblePersonaJuridica.isEmpty()) {
      context.status(HttpStatus.NOT_FOUND);
      return;
    }

    Map<String, Object> model = new HashMap<>();
    model.put("personaJuridica", posiblePersonaJuridica.get());
    model.put("edicion", true);

    context.render("personas-juridicas/detalle_persona_juridica.hbs", model);
  }


  @Override
  public void update(Context context) {
    Long personaJuridicaId = Long.valueOf(context.pathParam("id"));

    Optional<PersonaJuridica> posiblePersonaJuridica = repositorioPersonaJuridica.buscarPorId(personaJuridicaId);

    if (posiblePersonaJuridica.isEmpty()) {
      context.status(HttpStatus.NOT_FOUND);
      context.result("La persona jurídica con el ID " + personaJuridicaId + " no fue encontrada.");
      return;
    }
    PersonaJuridica personaJuridica = posiblePersonaJuridica.get();
    String razonSocial = context.formParam("razonSocial");
    String tipoJuridicoStr = context.formParam("tipoJuridico");
    String rubroNombre = context.formParam("rubro");
    String puntosAcumuladosStr = context.formParam("puntosAcumulados");

    if (razonSocial != null) {
      personaJuridica.setRazonSocial(razonSocial);
    }

    if (tipoJuridicoStr != null) {
      TipoJuridico tipoJuridico = TipoJuridico.valueOf(tipoJuridicoStr.toUpperCase());
      personaJuridica.setTipoJuridico(tipoJuridico);
    }
    if (rubroNombre != null) {
      Rubro rubro = new Rubro();
      rubro.setNombre(rubroNombre);
      personaJuridica.setRubro(rubro);
    }

    if (puntosAcumuladosStr != null) {
      personaJuridica.setPuntosAcumulados(Double.valueOf(puntosAcumuladosStr));
    }
    repositorioPersonaJuridica.guardar(personaJuridica);

    // Devolver una respuesta exitosa
    context.status(HttpStatus.OK);
    context.result("Persona jurídica actualizada con éxito.");

  }

  @Override
  public void delete(Context context) {

    Long personaJuridicaId = Long.valueOf(context.pathParam("id"));
    Optional<PersonaJuridica> posiblePersonaJuridica = repositorioPersonaJuridica.buscarPorId(personaJuridicaId);

    if (posiblePersonaJuridica.isEmpty()) {
      context.status(HttpStatus.NOT_FOUND);
      context.result("La persona jurídica con el ID " + personaJuridicaId + " no fue encontrada.");
      return;
    }

    repositorioPersonaJuridica.eliminar(personaJuridicaId);

    context.status(HttpStatus.NO_CONTENT); // 204 No Content es común para operaciones de eliminación
    context.result("Persona jurídica eliminada con éxito.");
  }

  public void registroExito(Context context){
      String titulo = "Registro exitoso";
      String mensaje = "Realizaste tu registro como colaborador con éxito. ¡Gracias por sumarte!";
      context.render("views/estados/success.hbs", Map.of("titulo", titulo, "mensaje", mensaje));

  }
}

