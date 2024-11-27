package ar.edu.utn.frba.dds.grupo05.controllers;
import io.javalin.http.Context;

public interface ICrudViewsHandler {
  void index(Context context);
  void show(Context context);
  void create(Context context) throws InterruptedException;
  void save(Context context);
  void edit(Context context);
  void update(Context context);
  void delete(Context context);
}
