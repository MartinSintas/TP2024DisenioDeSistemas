package ar.edu.utn.frba.dds.grupo05.services;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DistribucionVianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.DistribucionViandaInputDTO;

import java.util.List;

public interface IDistribucionViandaService {

  void registrarDistribucionVianda(DistribucionVianda distribucionVianda, Usuario usuario);

  DistribucionVianda obtenerPorId(Long id);

  List<DistribucionVianda> obtenerTodasLasDistribuciones();

  void actualizarDistribucionVianda(DistribucionVianda distribucion);

  void eliminarDistribucionVianda(Long id);

  List<Vianda> obtenerViandasPorIds(List<String> idsViandas);
}

