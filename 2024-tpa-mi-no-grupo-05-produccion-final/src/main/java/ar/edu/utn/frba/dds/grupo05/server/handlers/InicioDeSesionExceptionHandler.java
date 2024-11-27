package ar.edu.utn.frba.dds.grupo05.server.handlers;

import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

public class InicioDeSesionExceptionHandler implements IHandler{
  @Override
  public void setHandle(Javalin app) {
    app.exception(ar.edu.utn.frba.dds.grupo05.exceptions.InicioDeSesionException.class, (e, ctx) -> {
      ctx.status(HttpStatus.UNAUTHORIZED);
      ctx.result("Error al iniciar sesión: no se encontro esa combinación de usuario y contraseña");
    });
  }
}
