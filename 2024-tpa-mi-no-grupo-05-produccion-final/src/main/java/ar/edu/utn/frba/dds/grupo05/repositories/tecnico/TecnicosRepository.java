package ar.edu.utn.frba.dds.grupo05.repositories.tecnico;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.repositories.ITecnicoRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;

public class TecnicosRepository extends Repositorio implements ITecnicoRepository {

  private List<Tecnico> tecnicos;


  /*
  @Override
  public Optional<Tecnico> buscarPorZonas(Localidad localidad) {
    return Optional.empty();
  }
  */
/*
  @Override
  public List<Tecnico> buscarPorZona(Localidad localidad) {
    return List.of();
  }*/

  // TODO: buscar por id?



  public List<Tecnico> buscarTodosLosTecnicos() {
    return (List<Tecnico>) (List<?>) this.buscarTodos("tecnico");
  }

  @Override
  public void actualizarTecnico(Optional<Tecnico> tecnico) {
    this.actualizar(tecnico);
  }

  @Override
  public void eliminarTecnico(Optional<Tecnico> tecnico) {
    this.eliminar(tecnico);
  }

  @Override
  public void guardarTecnico(Tecnico tecnico) {
    this.guardar(tecnico);
  }

  @Override
  public Optional<Tecnico> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(Tecnico.class, id));
  }


  @Override
  public List<Tecnico> buscarPorZona(Localidad localidad) {
    return List.of();
  }
}

