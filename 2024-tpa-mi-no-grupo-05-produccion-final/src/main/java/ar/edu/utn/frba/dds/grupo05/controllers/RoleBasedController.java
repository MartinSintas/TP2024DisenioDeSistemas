package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import io.javalin.http.Context;

import java.util.HashMap;

public abstract class RoleBasedController {

  private Boolean esRol(Context context, TipoRol rol) {
    TipoRol rolUsuario = context.sessionAttribute("rol");
    return rolUsuario != null && rolUsuario.equals(rol);
  }

  public Boolean esAdministrador(Context context) {
    return esRol(context, TipoRol.ADMINISTRADOR);
  }

  public Boolean esPersonaHumana(Context context) {
    return esRol(context, TipoRol.PERSONA_HUMANA);
  }

  public Boolean esPersonaJuridica(Context context) {
    return esRol(context, TipoRol.PERSONA_JURIDICA);
  }
  public Boolean esTecnico(Context context) {
    return esRol(context, TipoRol.TECNICO);
  }
  public Boolean noTieneRol(Context context) {
    return context.sessionAttribute("rol") == null;
  }

  public Boolean tieneSesion(Context context) {
    return context.sessionAttribute("usuario") != null;
  }

  public Boolean noTieneSesion(Context context) {
    return !tieneSesion(context);
  }


  protected HashMap<String, Object> getModel(Context context) {
    HashMap<String, Object> model = new HashMap<>();
    model.put("esAdministrador", esAdministrador(context));
    model.put("esPersonaHumana", esPersonaHumana(context));
    model.put("esPersonaJuridica", esPersonaJuridica(context));
    model.put("esTecnico", esTecnico(context));
    model.put("noTieneRol", noTieneRol(context));
    model.put("tieneSesion", tieneSesion(context));
    model.put("noTieneSesion", noTieneSesion(context));
    return model;
  }
}
