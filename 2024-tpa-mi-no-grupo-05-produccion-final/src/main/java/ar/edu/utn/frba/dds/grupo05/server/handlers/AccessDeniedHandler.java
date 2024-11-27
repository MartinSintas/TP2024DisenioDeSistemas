package ar.edu.utn.frba.dds.grupo05.server.handlers;

import ar.edu.utn.frba.dds.grupo05.exceptions.AccessDeniedException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class AccessDeniedHandler implements IHandler {

  @Override
  public void setHandle(Javalin app) {
    app.exception(AccessDeniedException.class, (e, context) -> {
      context.status(401);
      Map<String, String> model = new HashMap<>();
      model.put("titulo", "Acceso denegado");
      model.put("mensaje", "Alto ahí cerebrito, no tenés permisos para hacer eso.");
      context.render("views/estados/error.hbs", model);
    });
  }
}