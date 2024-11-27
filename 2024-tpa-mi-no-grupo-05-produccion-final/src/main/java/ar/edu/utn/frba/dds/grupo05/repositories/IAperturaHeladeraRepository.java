package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.AperturaHeladera;

import java.util.Optional;

public interface IAperturaHeladeraRepository {
    Optional<AperturaHeladera> findById(Long id);
    void save(AperturaHeladera aperturaHeladera);
}
