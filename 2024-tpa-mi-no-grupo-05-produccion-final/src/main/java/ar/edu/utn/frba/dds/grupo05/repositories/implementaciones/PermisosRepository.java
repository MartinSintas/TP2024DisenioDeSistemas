package ar.edu.utn.frba.dds.grupo05.repositories.implementaciones;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Permiso;
import ar.edu.utn.frba.dds.grupo05.repositories.IPermisosRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PermisosRepository implements IPermisosRepository {
  private List<Permiso> permisos;
  public PermisosRepository(){
    this.permisos = new ArrayList<>();
  }
  @Override
  public Optional<Permiso> buscarPorNombre(String nombre){
    return this.permisos
        .stream()
        .filter(c -> c.getNombre().equals(nombre))
        .findFirst();
  }


}
