package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;

import java.util.List;
import java.util.Optional;

public interface ITecnicoRepository {

  void guardarTecnico(Tecnico tecnico);

  Optional<Tecnico> buscarPorId(Long id);

  void eliminarTecnico(Optional<Tecnico> tecnico);
  void actualizarTecnico(Optional<Tecnico> tecnico);

  List<Tecnico> buscarPorZona(Localidad localidad);

}
