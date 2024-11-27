package ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios;

import io.javalin.security.RouteRole;

public enum TipoRol implements RouteRole {
  ADMINISTRADOR,
  PERSONA_HUMANA,
  PERSONA_JURIDICA,
  TECNICO
}
