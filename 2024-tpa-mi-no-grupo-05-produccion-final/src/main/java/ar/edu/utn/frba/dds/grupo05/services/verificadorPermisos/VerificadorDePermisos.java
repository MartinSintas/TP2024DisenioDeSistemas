package ar.edu.utn.frba.dds.grupo05.services.verificadorPermisos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Permiso;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.repositories.IPermisosRepository;

import java.util.Optional;

public class VerificadorDePermisos {
  private IPermisosRepository permisosRepository;
  public  VerificadorDePermisos(IPermisosRepository permisosRepository){
    this.permisosRepository = permisosRepository;
  }

  public VerificadorDePermisos() {

  }

  public void verificarSiUsuarioPuede(String accion, Usuario usuario){
    Optional<Permiso> permisoBuscado = this.permisosRepository.buscarPorNombre(accion);
    if(permisoBuscado.isEmpty())
      throw new RuntimeException("No existe un permiso con el nombre " + accion);

    Permiso permiso = permisoBuscado.get();

   // if(!usuario.getRol().comprobarPermiso(permiso))
   //   throw new UsuarioSinPermisoException("Usted no tiene permiso para: " + accion);
  }

}
