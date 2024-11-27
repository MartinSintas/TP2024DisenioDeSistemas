package ar.edu.utn.frba.dds.grupo05.repositories.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorTemperatura;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;

public class RepositorioSensorTemperatura extends Repositorio {

    public Optional<SensorTemperatura> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(SensorTemperatura.class, id));
    }
    public void guardarSensorTemperatura(SensorTemperatura sensorTemperatura) {
        this.guardar(sensorTemperatura);
    }

    public List<SensorTemperatura> buscarSensorTemperatura() {
        return (List<SensorTemperatura>) (List<?>) this.buscarTodos("sensor_temperatura");
    }

    public void actualizarSensorTemperatura(SensorTemperatura sensorTemperatura) {
        this.actualizar(sensorTemperatura);
    }

    public void eliminarSensorTemperatura(SensorTemperatura sensorTemperatura) {
        this.eliminar(sensorTemperatura);
    }
}
