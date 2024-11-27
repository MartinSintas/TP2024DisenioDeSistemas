package ar.edu.utn.frba.dds.grupo05.server.middleware;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import ar.edu.utn.frba.dds.grupo05.exceptions.AccessDeniedException;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class AuthMiddleware {

  public static void apply(Javalin app) {
    app.beforeMatched(ctx -> {
      var userRole = getUserRoleType(ctx);
      if (!ctx.routeRoles().isEmpty() && !ctx.routeRoles().contains(userRole)) {
        throw new AccessDeniedException();
      }
    });
  }

  private static TipoRol getUserRoleType(Context context) {
    return context.sessionAttribute("rol") != null?
            context.sessionAttribute("rol") : null;
  }
}