package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorTemperatura;

import java.util.Optional;

public interface ISensorTemperaturaRepository {
    Optional<SensorTemperatura> findById(Long id);
    void save(SensorTemperatura sensor);
}
