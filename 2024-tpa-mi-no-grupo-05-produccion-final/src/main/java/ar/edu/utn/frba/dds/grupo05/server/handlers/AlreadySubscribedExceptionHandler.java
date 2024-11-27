package ar.edu.utn.frba.dds.grupo05.server.handlers;

import ar.edu.utn.frba.dds.grupo05.services.AlreadySubscribedException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class AlreadySubscribedExceptionHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(AlreadySubscribedException.class, (e, ctx) -> {
      ctx.status(400);
      Map<String, String> model = new HashMap<>();
      model.put("titulo", "Error en la suscripción");
      model.put("mensaje", "Ya tienes ese tipo de suscripción para la heladera.");
      ctx.render("/views/estados/error.hbs", model);
    });
  }
}
