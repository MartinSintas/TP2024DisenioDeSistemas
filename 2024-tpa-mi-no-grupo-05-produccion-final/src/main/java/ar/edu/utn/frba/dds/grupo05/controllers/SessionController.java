package ar.edu.utn.frba.dds.grupo05.controllers;


import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.services.usuario.UsuarioService;
import io.javalin.http.Context;

public class SessionController {
    UsuarioService usuarioService;

    public SessionController(UsuarioService usuarioService) {
      this.usuarioService = usuarioService;
    }

    public void iniciarSesionPage(Context context) {
      context.render("views/usuarios/iniciosesion.hbs");
    }

    public void createSession(Context context) {
      Usuario usuario = usuarioService.buscarUsuarioPorNombre(context.formParam("usuario"), context.formParam("contrasena"));
      context.sessionAttribute("usuario", usuario);
      context.sessionAttribute("rol", usuario.getRol());
      context.redirect("/");
    }

    public void destroySession(Context context) {
        context.req().getSession().invalidate();
        context.redirect("/");
    }
}
