package ar.edu.utn.frba.dds.grupo05.repositories.usuario;

import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.Formulario;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.Optional;

public class RepositorioUsuario extends Repositorio {
  public Optional<Usuario> buscarPorNombre(String username){
    String query = "SELECT u FROM Usuario u WHERE u.nombreDeUsuario = :username";
    return Optional.ofNullable(entityManager().createQuery(query, Usuario.class)
            .setParameter("username", username)
            .getSingleResult());
  }
}
