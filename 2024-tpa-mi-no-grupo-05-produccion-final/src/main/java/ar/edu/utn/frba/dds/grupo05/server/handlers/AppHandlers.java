package ar.edu.utn.frba.dds.grupo05.server.handlers;

import io.javalin.Javalin;

import java.util.Arrays;

public class AppHandlers {
  private IHandler[] handlers = new IHandler[]{
          new ColaboradorSinTarjetaExceptionHandler(),
          new AlreadySubscribedExceptionHandler(),
          new ContraseniaDebilExceptionHandler(),
          new AccessDeniedHandler(),
          new AlreadySubscribedExceptionHandler(),
          new ContraseniaDebilExceptionHandler(),
          new InicioDeSesionExceptionHandler(),
          new InternalServerErrorHandler(),
          new PuntosInsuficientesHandler(),
          new ValidationExceptionHandler()
  };

  public static void applyHandlers(Javalin app) {
    Arrays.stream(new AppHandlers().handlers).toList().forEach(handler -> handler.setHandle(app));
  }
}