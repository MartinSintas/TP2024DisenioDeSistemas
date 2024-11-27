package ar.edu.utn.frba.dds.grupo05.server.handlers;

import ar.edu.utn.frba.dds.grupo05.exceptions.PuntosInsuficientesException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class PuntosInsuficientesHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(PuntosInsuficientesException.class, (e, context) -> {
            context.status(400);

            Map<String, String> model = new HashMap<>();
            model.put("titulo", "Puntos insuficientes");
            model.put("mensaje", "Actualmente no tienes los puntos suficientes para realizar el canje. Intenta de nuevo más tarde");
            context.render("views/estados/error.hbs", model);
        });
    }
}
