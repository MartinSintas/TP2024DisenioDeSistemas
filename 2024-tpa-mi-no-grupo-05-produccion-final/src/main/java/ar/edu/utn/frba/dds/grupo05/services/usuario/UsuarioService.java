package ar.edu.utn.frba.dds.grupo05.services.usuario;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.UsuarioInputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.InicioDeSesionException;
import ar.edu.utn.frba.dds.grupo05.repositories.usuario.RepositorioUsuario;
import ar.edu.utn.frba.dds.grupo05.utils.validadorContrasenias.validadores.Validador;

import java.util.Optional;

public class UsuarioService implements IUsuarioService{

  private RepositorioUsuario repositorioUsuario;
  private Validador validador;

  public UsuarioService(RepositorioUsuario repositorioUsuario, Validador validador) {
    this.repositorioUsuario = repositorioUsuario;
    this.validador = validador;
  }

  @Override
  public Usuario darAltaUsuario(UsuarioInputDTO usuarioInputDTO) {
    validador.validarContrasenia(usuarioInputDTO.getContrasenia());
    Usuario usuario = new Usuario();
    usuario.setNombreDeUsuario(usuarioInputDTO.getNombreDeUsuario());
    usuario.setContrasenia(usuarioInputDTO.getContrasenia());
    usuario.setRol(usuarioInputDTO.getRol());;
    this.repositorioUsuario.guardar(usuario);
    return usuario;
  }

  @Override
  public Usuario buscarUsuarioPorNombre(String username, String password) {
    Optional<Usuario> posibleUsuario = this.repositorioUsuario.buscarPorNombre(username);

    if (posibleUsuario.isEmpty()){
      //TODO: Tirar excepcion
      throw new InicioDeSesionException();
    }

      //TODO: seguridad¿
    Usuario user = posibleUsuario.get();
    if(!user.getContrasenia().equals(password)){
      throw new InicioDeSesionException();
    }

    return user;
  }
}
