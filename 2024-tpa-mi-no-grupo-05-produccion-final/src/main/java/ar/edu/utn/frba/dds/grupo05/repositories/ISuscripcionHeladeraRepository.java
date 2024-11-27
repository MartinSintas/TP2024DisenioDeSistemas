package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones.SuscripcionHeladera;

import java.util.List;

public interface ISuscripcionHeladeraRepository {
    List<SuscripcionHeladera> findAll();
}
