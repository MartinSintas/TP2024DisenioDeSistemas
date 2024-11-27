package ar.edu.utn.frba.dds.grupo05.repositories.sensores;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorTemperatura;
import ar.edu.utn.frba.dds.grupo05.repositories.ISensorTemperaturaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SensorTemperaturaListRepository implements ISensorTemperaturaRepository {
    List<SensorTemperatura> sensores = new ArrayList<>();

    @Override
    public Optional<SensorTemperatura> findById(Long id) {
        for (SensorTemperatura sensor : sensores) {
            if (sensor.getId().equals(id)) {
                return Optional.of(sensor);
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(SensorTemperatura sensor) {
        // if not in list, save
        if (!sensores.contains(sensor)) {
            sensores.add(sensor);
        }
    }


}
