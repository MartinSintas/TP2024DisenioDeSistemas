package ar.edu.utn.frba.dds.grupo05.server.handlers;

import ar.edu.utn.frba.dds.grupo05.exceptions.ContraseniaDebilException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class ContraseniaDebilExceptionHandler implements IHandler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(ContraseniaDebilException.class, (e, context) -> {
      context.status(400);

      // TODO: implementar mejor mensaje de error con detalle de por qué la contraseña es débil
      Map<String, String> model = new HashMap<>();
      model.put("titulo", "Error de validación");
      model.put("mensaje", "La contraseña es débil. Por favor, elija una contraseña más segura.");
      context.render("views/estados/error.hbs", model);
    });
  }
}
