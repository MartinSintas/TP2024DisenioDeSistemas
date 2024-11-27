package ar.edu.utn.frba.dds.grupo05.services.usuario;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.UsuarioInputDTO;

public interface IUsuarioService {
  Usuario darAltaUsuario(UsuarioInputDTO usuarioInputDTO);

  Usuario buscarUsuarioPorNombre(String username, String password);
}
