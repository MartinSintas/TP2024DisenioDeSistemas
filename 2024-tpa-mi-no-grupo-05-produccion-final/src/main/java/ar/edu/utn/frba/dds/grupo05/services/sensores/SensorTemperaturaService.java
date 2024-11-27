package ar.edu.utn.frba.dds.grupo05.services.sensores;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.EstadoHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.*;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorTemperatura;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.RegistroTemperaturaDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.SensorTemperaturaNotFoundException;
import ar.edu.utn.frba.dds.grupo05.repositories.IAlertaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.ISensorTemperaturaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioAlerta;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioSensorTemperatura;
import ar.edu.utn.frba.dds.grupo05.services.fallatecnica.IncidenteService;
import net.bytebuddy.asm.Advice;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

public class SensorTemperaturaService implements ISensorTemperaturaService{
    private RepositorioSensorTemperatura sensorTemperaturaRepository;
    private RepositorioAlerta alertaRepository;
    private RepositorioHeladera heladeraRepository;
    private IncidenteService incidenteService;

    public SensorTemperaturaService(
            RepositorioSensorTemperatura sensorTemperaturaRepository,
            RepositorioAlerta alertaRepository,
            RepositorioHeladera heladeraRepository,
            IncidenteService incidenteService
    ) {
        this.sensorTemperaturaRepository = sensorTemperaturaRepository;
        this.alertaRepository = alertaRepository;
        this.heladeraRepository = heladeraRepository;
        this.incidenteService = incidenteService;
    }

    @Override
    public void registrarTemperatura(RegistroTemperaturaDTO registroTemperatura) {
        Long id = registroTemperatura.getIdSensor();
        Optional<SensorTemperatura> posibleSensorTemperatura = sensorTemperaturaRepository.buscarPorId(id);

        if (posibleSensorTemperatura.isEmpty()) {
            throw new SensorTemperaturaNotFoundException("Sensor de temperatura con id " + id + "no encontrado.");
        }

        SensorTemperatura sensorTemperatura = posibleSensorTemperatura.get();

        Double reg = Double.valueOf(registroTemperatura.getTemperatura());
        sensorTemperatura.recibirTemperatura(reg);

        Heladera heladera = sensorTemperatura.getHeladera();

        heladeraRepository.guardar(heladera);

        if (heladera.getEstadoHeladera() == EstadoHeladera.DESCOMPUESTA) {
            Alerta alerta = new Alerta(LocalDateTime.now(), heladera, TipoAlerta.TEMPERATURA);

            Incidente incidente = incidenteService.crearIncidente(heladera, TipoIncidente.ALERTA);

            alerta.setIncidente(incidente);
            alertaRepository.guardar(alerta);
        }

    }
}
