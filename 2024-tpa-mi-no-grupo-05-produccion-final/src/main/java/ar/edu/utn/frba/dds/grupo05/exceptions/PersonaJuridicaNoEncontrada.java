package ar.edu.utn.frba.dds.grupo05.exceptions;

public class PersonaJuridicaNoEncontrada extends RuntimeException{
  public PersonaJuridicaNoEncontrada(){}
  public PersonaJuridicaNoEncontrada(String mensaje){
    super(mensaje);
  }
}

