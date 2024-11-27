package ar.edu.utn.frba.dds.grupo05.server.handlers;

import io.javalin.Javalin;

public interface IHandler {
  void setHandle(Javalin app);
}