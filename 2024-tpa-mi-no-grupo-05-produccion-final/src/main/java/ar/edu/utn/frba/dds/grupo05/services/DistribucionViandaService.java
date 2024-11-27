package ar.edu.utn.frba.dds.grupo05.services;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DistribucionVianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.DistribucionViandaInputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.DistribucionViandaNotFoundException;
import ar.edu.utn.frba.dds.grupo05.exceptions.HeladeraNotFoundException;
import ar.edu.utn.frba.dds.grupo05.exceptions.ViandaNotFoundException;
import ar.edu.utn.frba.dds.grupo05.factories.DistribucionViandaFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas.RepositorioDistribucionVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioHeladera;
import ar.edu.utn.frba.dds.grupo05.services.personashumanasservices.IPersonaHumanaService;
import ar.edu.utn.frba.dds.grupo05.services.personashumanasservices.PersonaHumanaService;
import ar.edu.utn.frba.dds.grupo05.services.usuario.IUsuarioService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DistribucionViandaService implements IDistribucionViandaService {

  private final RepositorioDistribucionVianda distribucionViandaRepository;
  private final IPersonaHumanaService personaHumanaService;

  public DistribucionViandaService(RepositorioDistribucionVianda distribucionViandaRepository,
                                   IPersonaHumanaService personaHumanaService) {
    this.distribucionViandaRepository = distribucionViandaRepository;
    this.personaHumanaService = personaHumanaService;
  }

  @Override
  public void registrarDistribucionVianda(DistribucionVianda distribucionVianda, Usuario usuario) {
    PersonaHumana personaHumana = personaHumanaService.obtenerPorUsuario(usuario);
    distribucionVianda.setPersonaHumana(personaHumana);
    distribucionViandaRepository.guardar(distribucionVianda);
  }

  @Override
  public DistribucionVianda obtenerPorId(Long id) {
    return distribucionViandaRepository.buscarPorId(id)
        .orElseThrow(() -> new DistribucionViandaNotFoundException("Distribución no encontrada con ID " + id));
  }

  @Override
  public List<DistribucionVianda> obtenerTodasLasDistribuciones() {
    return distribucionViandaRepository.buscarDistribucionVianda();
  }

  @Override
  public void actualizarDistribucionVianda(DistribucionVianda distribucion) {
    distribucionViandaRepository.actualizar(distribucion);
  }

  @Override
  public void eliminarDistribucionVianda(Long id) {
    DistribucionVianda distribucion = obtenerPorId(id);
    distribucionViandaRepository.eliminar(distribucion);
  }

  @Override
  public List<Vianda> obtenerViandasPorIds(List<String> idsViandas) {
    List<Long> longIdsViandas = idsViandas.stream()
        .map(Long::parseLong)  // Convierte cada String a Long
        .collect(Collectors.toList());
    List<Vianda> viandas = new ArrayList<>();
    for (Long id : longIdsViandas) {
      Vianda vianda = (Vianda) distribucionViandaRepository.buscarPorId(Vianda.class, id).orElseThrow(() -> new ViandaNotFoundException("No se encontro la vianda de id: {}", id));
      viandas.add(vianda);  // Solo agrega si se encuentra
    }
    return viandas;
  }
}

