package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Permiso;

import java.util.Optional;

public interface IPermisosRepository {


    Optional<Permiso> buscarPorNombre(String nombre);
  }

