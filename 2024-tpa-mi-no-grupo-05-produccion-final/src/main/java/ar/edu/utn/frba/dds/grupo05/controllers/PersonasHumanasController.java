package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.FormaDeColaborar;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.*;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.TipoContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.MedioDeContactoDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaHumanaInputDTO;
import ar.edu.utn.frba.dds.grupo05.repositories.formularios.RepositorioFormulario;
import ar.edu.utn.frba.dds.grupo05.services.personashumanasservices.PersonaHumanaService;
import io.javalin.http.Context;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.*;

public class PersonasHumanasController implements ICrudViewsHandler{

  private PersonaHumanaService personaHumanaService;
  private RepositorioFormulario repositorioFormulario;

  public PersonasHumanasController(PersonaHumanaService personaHumanaService, RepositorioFormulario repositorioFormulario) {
    this.personaHumanaService = personaHumanaService;
    this.repositorioFormulario = repositorioFormulario;
  }

  @Override
  public void index(Context context){
    List<PersonaHumana> personas = personaHumanaService.obtenerTodas();
    Map<String, Object> model = new HashMap<>();
    model.put("personas", personas);

    context.render("views/personas/registro-personas.hbs", model);

  }

  @Override
  public void show(Context context){

  }

  @Override
  public void create(Context context){
    //List<Enunciado> enunciados = this.personaHumanaService.obtenerEnunciadosDinamicos();

    Map<String, Object> model = new HashMap<>();
    //model.put("enunciados", enunciados);

    context.render("views/registros/registro-persona-humana.hbs", model);
  }

  @Override
  public void save(Context context){

    //RespuestasFormulario respuestasFormulario = obtenerRespuestasFormularioDinamico(context);

    String fechaNacimiento = context.formParam("fechaNacimiento");


    PersonaHumanaInputDTO personaHumanaInputDTO = PersonaHumanaInputDTO
            .builder()
            .usuario(context.formParam("usuario"))
            .contrasenia(context.formParam("contrasena"))
            .nombre(context.formParam("nombre"))
            .apellido(context.formParam("apellido"))
            .fechaNacimiento(
                    fechaNacimiento != null ?
                    LocalDate.parse(fechaNacimiento) : null)
            .tipoDocumento(TipoDocumento.valueOf(context.formParam("tipoDocumento")))
            .nroDocumento(context.formParam("numeroDocumento"))
            .latitudDireccion(context.formParam("latitude"))
            .longitudDireccion(context.formParam("longitude"))
            .medioDeContacto(getMedioDeContacto(context))
            //.respuestasFormularioDinamico(respuestasFormulario)
            .build();

    agregarFormasDeColaborar(context, personaHumanaInputDTO);
    this.personaHumanaService.darDeAlta(personaHumanaInputDTO);
    // Redirect to success
    this.registroExito(context);
  }

  private static void agregarFormasDeColaborar(Context context, PersonaHumanaInputDTO personaHumanaInputDTO) {
    List<String> formasDeColaborar = context.formParams("formaDeColaborar");
    formasDeColaborar.forEach((f) -> personaHumanaInputDTO.addFormaDeColaborar(FormaDeColaborar.valueOf(f)));
  }

  public void registroExito(Context context){
    String titulo = "Registro exitoso";
    String mensaje = "Realizaste tu registro como colaborador particular con éxito. ¡Gracias por sumarte!";
    context.render("views/estados/success.hbs", Map.of("titulo", titulo, "mensaje", mensaje));
  }

  @Override
  public void edit(Context context){

  }

  @Override
  public void update(Context context){

  }

  @Override
  public void delete(Context context){

  }

  private MedioDeContactoDTO getMedioDeContacto(Context context) {

    String tipoMedioDeContacto = context.formParam("medioContacto");
    String valorMedioDeContacto = context.formParam("contactoValor");
    if (tipoMedioDeContacto != null && valorMedioDeContacto !=null) {
      return MedioDeContactoDTO
              .builder()
              .tipo(TipoContacto.valueOf(tipoMedioDeContacto))
              .valor(valorMedioDeContacto)
              .build();
    }
    return null;
  }

  private RespuestasFormulario obtenerRespuestasFormularioDinamico(Context context) {

    Optional<Formulario> posibleFormulario = repositorioFormulario.buscarPorTipoFormulario(TipoFormulario.PERSONA_HUMANA);

    if (posibleFormulario.isPresent()) {
      Formulario formulario = posibleFormulario.get();
      List<Enunciado> enunciados = formulario.getEnunciados();
      RespuestasFormulario respuestasFormulario = new RespuestasFormulario();
      for (Enunciado enunciado : enunciados) {
        String valor = context.formParam(enunciado.getName());
        respuestasFormulario.addRespuesta(
                Respuesta
                        .builder()
                        .enunciado(enunciado)
                        .desarrollo(valor)
                        .build()
        );
      }
      respuestasFormulario.setFormulario(formulario);
      return respuestasFormulario;
    }

    return null;
  }
}
