package ar.edu.utn.frba.dds.grupo05.services.verificadorPermisos;

public class UsuarioSinPermisoException extends RuntimeException{
  public UsuarioSinPermisoException(){

  }
  public UsuarioSinPermisoException(String mensaje){
    super(mensaje);
  }
}
