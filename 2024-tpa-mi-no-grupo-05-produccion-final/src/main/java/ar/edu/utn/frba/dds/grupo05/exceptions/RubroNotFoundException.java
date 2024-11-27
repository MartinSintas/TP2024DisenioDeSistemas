package ar.edu.utn.frba.dds.grupo05.exceptions;

public class RubroNotFoundException extends RuntimeException {

  public RubroNotFoundException(String s) {
    super("No se encontro un Rubro con nombre: "+ s);
  }
}

