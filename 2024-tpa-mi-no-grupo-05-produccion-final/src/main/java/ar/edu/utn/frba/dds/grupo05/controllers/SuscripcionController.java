package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.dtos.SuscripcionInputDTO;
import ar.edu.utn.frba.dds.grupo05.services.ISuscripcionService;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

import java.util.Map;

public class SuscripcionController extends RoleBasedController {

  public ISuscripcionService suscripcionService;

  public SuscripcionController(ISuscripcionService suscripcionService) {
    this.suscripcionService = suscripcionService;
  }

  public void crear(Context context) {

    Long idHeladera = context.formParamAsClass("fridgeId", Long.class).getOrThrow(ValidationException::new);

    String tipoSuscripcion = context.formParamAsClass("subscriptionType", String.class).check(
            t -> t.equals("FALTAN_VIANDAS") || t.equals("HAY_VIANDAS") || t.equals("INCIDENTE"),
            "Tipo de suscripción inválido, debe ser FALTAN_VIANDAS, HAY_VIANDAS o INCIDENTE"
    ).get();

    Integer numeroCritico = null;
    if (!tipoSuscripcion.equals("INCIDENTE")) {
      numeroCritico = context.formParamAsClass("criticalNumber", Integer.class).check(
              n -> n > 0,
              "El número crítico debe ser mayor a 0"
      ).get();
    }

    SuscripcionInputDTO suscripcionInputDTO = SuscripcionInputDTO.builder()
            .idHeladera(idHeladera)
            .tipoSuscripcion(tipoSuscripcion)
            .usuario(context.sessionAttribute("usuario"))
            .numeroCritico(numeroCritico)
            .build();


    this.suscripcionService.crearSuscripcion(suscripcionInputDTO);

    Map<String, Object> model = this.getModel(context);
    model.put("titulo", "Suscripción creada con éxito");
    model.put("mensaje", this.obtenerMensajeExitoSuscripcion(tipoSuscripcion, numeroCritico));
    context.render("views/estados/success.hbs", model);
  }

  private String obtenerMensajeExitoSuscripcion(String tipoSuscripcion, Integer numeroCritico) {
    return switch (tipoSuscripcion) {
      case "FALTAN_VIANDAS" -> "Te notificaremos cuando falten " + numeroCritico + " viandas en la heladera";
      case "HAY_VIANDAS" -> "Te notificaremos cuando haya " + numeroCritico + " viandas en la heladera";
      case "FALLA_TECNICA" -> "Te notificaremos cuando haya un desperfecto técnico en la heladera";
      default -> "Te notificaremos cuando se cumpla la condición de la suscripción";
    };
  }
}
