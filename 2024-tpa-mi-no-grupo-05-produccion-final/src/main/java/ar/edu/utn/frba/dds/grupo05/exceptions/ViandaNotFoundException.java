package ar.edu.utn.frba.dds.grupo05.exceptions;

public class ViandaNotFoundException extends RuntimeException {
  public ViandaNotFoundException(String s, Long id) {
    super(s + id);
  }
}
