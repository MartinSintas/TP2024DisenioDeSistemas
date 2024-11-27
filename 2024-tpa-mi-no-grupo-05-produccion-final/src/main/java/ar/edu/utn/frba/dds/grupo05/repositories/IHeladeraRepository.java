package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;

import java.util.List;
import java.util.Optional;

public interface IHeladeraRepository {
    List<Heladera> buscarTodas();
    Optional<Heladera> buscarPorId(Long id);
    void guardar(Heladera heladera);
}
