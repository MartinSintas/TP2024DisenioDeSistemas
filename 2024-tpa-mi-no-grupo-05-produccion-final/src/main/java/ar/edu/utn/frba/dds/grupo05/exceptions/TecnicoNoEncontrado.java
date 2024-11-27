package ar.edu.utn.frba.dds.grupo05.exceptions;

public class TecnicoNoEncontrado extends RuntimeException{
  public TecnicoNoEncontrado(){}
  public TecnicoNoEncontrado(String mensaje){
    super(mensaje);
  }
}
