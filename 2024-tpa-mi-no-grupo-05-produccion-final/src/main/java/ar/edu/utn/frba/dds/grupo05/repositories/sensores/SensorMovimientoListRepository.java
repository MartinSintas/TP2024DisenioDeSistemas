package ar.edu.utn.frba.dds.grupo05.repositories.sensores;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorMovimiento;
import ar.edu.utn.frba.dds.grupo05.repositories.ISensorMovimientoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SensorMovimientoListRepository implements ISensorMovimientoRepository {

    List<SensorMovimiento> sensores = new ArrayList<>();

    @Override
    public Optional<SensorMovimiento> findById(Long id) {
        for (SensorMovimiento sensor : sensores) {
            if (sensor.getId().equals(id)) {
                return Optional.of(sensor);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(SensorMovimiento sensor) {
        // if not in list, save
        if (!sensores.contains(sensor)) {
            sensores.add(sensor);
        }
    }
}
