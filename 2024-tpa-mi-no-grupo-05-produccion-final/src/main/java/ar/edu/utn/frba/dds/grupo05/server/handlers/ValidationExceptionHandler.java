package ar.edu.utn.frba.dds.grupo05.server.handlers;

import ar.edu.utn.frba.dds.grupo05.exceptions.ContraseniaDebilException;
import io.javalin.Javalin;
import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationExceptionHandler implements IHandler{
  @Override
  public void setHandle(Javalin app) {
    app.exception(ValidationException.class, (e, context) -> {
      context.status(400);

      Map<String, List<ValidationError<Object>>> errors = e.getErrors();

      String titulo = "Error de validación";
      StringBuilder mensaje = new StringBuilder("Hubo un error de validación. Por favor, revise los siguientes campos: ");

      for (Map.Entry<String, List<ValidationError<Object>>> entry : errors.entrySet()) {
        mensaje.append(entry.getKey()).append(", ");
      }

      Map<String, String> model = new HashMap<>();

      model.put("titulo", titulo);
      model.put("mensaje", mensaje.toString());
      context.render("views/estados/error.hbs", model);
    });
  }
}
