package ar.edu.utn.frba.dds.grupo05.server.handlers;

import ar.edu.utn.frba.dds.grupo05.exceptions.ColaboradorSinTarjetaException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class ColaboradorSinTarjetaExceptionHandler implements IHandler{
  @Override
  public void setHandle(Javalin app) {
    app.exception(ColaboradorSinTarjetaException.class, (e, context) -> {
      context.status(400);
      Map<String, Object> model = new HashMap<>();
      model.put("titulo", "Error haciendo la donación de vianda.");
      model.put("mensaje", "Todavía no te hemos entregado la tarjeta para hacer donaciones de viandas.");
      context.render("/views/estados/error.hbs", model);
    });
  }
}
