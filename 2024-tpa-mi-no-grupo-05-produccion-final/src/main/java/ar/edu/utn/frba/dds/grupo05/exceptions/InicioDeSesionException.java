package ar.edu.utn.frba.dds.grupo05.exceptions;

public class InicioDeSesionException extends RuntimeException {
  public InicioDeSesionException() {
    super("Error al iniciar sesión: no se encontro esa combinación de usuario y contraseña");
  }
}
