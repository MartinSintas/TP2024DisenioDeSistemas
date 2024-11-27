package ar.edu.utn.frba.dds.grupo05.services.sensores;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorTemperatura;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.RegistroTemperaturaDTO;

public interface ISensorTemperaturaService {
    void registrarTemperatura(RegistroTemperaturaDTO registroTemperaturaDTO);
}
