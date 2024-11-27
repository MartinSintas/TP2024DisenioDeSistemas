package ar.edu.utn.frba.dds.grupo05.server.handlers;

import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class InternalServerErrorHandler implements IHandler {

  @Override
  public void setHandle(Javalin app) {
    app.exception(Exception.class, (e, context) -> {
      context.status(500);
      Map<String, Object> model = new HashMap<>();
      model.put("titulo", "Error interno");
      model.put("mensaje", "Ocurrió un error interno en el servidor. Por favor, intente nuevamente más tarde.");
      context.render("/views/estados/error.hbs", model);
    });
  }
}
