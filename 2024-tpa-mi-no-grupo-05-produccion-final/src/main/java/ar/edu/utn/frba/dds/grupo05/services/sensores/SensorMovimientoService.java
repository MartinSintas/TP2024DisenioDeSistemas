package ar.edu.utn.frba.dds.grupo05.services.sensores;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.Alerta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorMovimiento;
import ar.edu.utn.frba.dds.grupo05.repositories.IAlertaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.ISensorMovimientoRepository;

import java.time.LocalDateTime;

public class SensorMovimientoService implements ISensorMovimientoService {

    private ISensorMovimientoRepository sensorMovimientoRepository;
    private IHeladeraRepository heladeraRepository;
    private IAlertaRepository alertaRepository;

    public SensorMovimientoService(ISensorMovimientoRepository sensorMovimientoRepository,
                                   IHeladeraRepository heladeraRepository,
                                   IAlertaRepository alertaRepository) {
        this.sensorMovimientoRepository = sensorMovimientoRepository;
        this.heladeraRepository = heladeraRepository;
        this.alertaRepository = alertaRepository;
    }

    @Override
    public void registrarMovimiento(Long idSensor) {
        SensorMovimiento sensor = sensorMovimientoRepository
                                    .findById(idSensor)
                                    .orElseThrow(() -> new RuntimeException("Sensor no encontrado"));

        sensor.alertarMovimiento();

        Heladera heladera = sensor.getHeladera();
        heladera.ponerEnAlertaMovimiento();
        heladeraRepository.guardar(heladera);

        Alerta alerta = new Alerta(LocalDateTime.now(), heladera, TipoAlerta.FRAUDE);

        alertaRepository.guardar(alerta);
    }
}
