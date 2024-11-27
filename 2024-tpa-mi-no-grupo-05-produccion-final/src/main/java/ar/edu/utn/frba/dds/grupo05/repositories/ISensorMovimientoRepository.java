package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorMovimiento;

import java.util.Optional;

public interface ISensorMovimientoRepository {
    Optional<SensorMovimiento> findById(Long id);
    void save(SensorMovimiento sensor);
}
