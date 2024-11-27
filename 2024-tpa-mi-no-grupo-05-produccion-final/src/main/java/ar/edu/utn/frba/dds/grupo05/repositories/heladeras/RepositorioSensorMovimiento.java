package ar.edu.utn.frba.dds.grupo05.repositories.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorMovimiento;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioSensorMovimiento extends Repositorio {

    public void guardarSensorMovimiento(SensorMovimiento sensorMovimiento) {
        this.guardar(sensorMovimiento);
    }

    public List<SensorMovimiento> buscarSensorMovimiento() {
        return (List<SensorMovimiento>) (List<?>) this.buscarTodos("sensor_movimiento");
    }

    public void actualizarSensorMovimiento(SensorMovimiento sensorMovimiento) {
        this.actualizar(sensorMovimiento);
    }

    public void eliminarSensorMovimiento(SensorMovimiento sensorMovimiento) {
        this.eliminar(sensorMovimiento);
    }
}
