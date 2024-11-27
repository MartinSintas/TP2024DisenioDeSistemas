package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.ModeloHeladeraDTO;

public class ModeloHeladeraFactory {

    public static ModeloHeladera fromDTO(ModeloHeladeraDTO modeloHeladeraDTO) {
        ModeloHeladera nuevoModeloHeladera = new ModeloHeladera();
        nuevoModeloHeladera.setDetalle(modeloHeladeraDTO.getDetalle());
        nuevoModeloHeladera.setTemperaturaMaxima(modeloHeladeraDTO.getTemperaturaMaxima());
        nuevoModeloHeladera.setTemperaturaMinima(modeloHeladeraDTO.getTemperaturaMinima());

        return nuevoModeloHeladera;
    }
}
