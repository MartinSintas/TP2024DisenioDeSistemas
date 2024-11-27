package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import io.javalin.http.Context;

import java.util.HashMap;

public class IndexController extends RoleBasedController {
  public void index(Context context) {
    HashMap<String, Object> model = this.getModel(context);
     context.render("views/index.hbs", model);
  }

  public void register(Context context) {
    HashMap<String, Object> model = this.getModel(context);
    context.render("views/menuregistro.hbs", model);
  }
}
