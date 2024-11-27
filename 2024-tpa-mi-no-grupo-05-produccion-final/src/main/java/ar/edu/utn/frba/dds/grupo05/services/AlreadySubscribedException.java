package ar.edu.utn.frba.dds.grupo05.services;

public class AlreadySubscribedException extends RuntimeException{
  public AlreadySubscribedException(Exception e) {
    super(e);
  }

  public AlreadySubscribedException() {
    super();
  }
}
