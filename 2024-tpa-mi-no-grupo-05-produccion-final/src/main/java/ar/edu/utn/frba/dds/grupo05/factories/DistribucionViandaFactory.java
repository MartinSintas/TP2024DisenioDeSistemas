package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DistribucionVianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.DistribucionViandaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.DistribucionOutputDTO;

import java.util.List;

public class DistribucionViandaFactory {

  public static DistribucionVianda crearDistribucionDesdeDTO(DistribucionViandaInputDTO inputDTO) {
    DistribucionVianda distribucion = new DistribucionVianda();


    distribucion.setHeladeraOrigen(inputDTO.getHeladeraOrigenId());
    distribucion.setHeladeraDestino(inputDTO.getHeladeraDestinoId());
    distribucion.setFecha(inputDTO.getFechaDistribucion());

    // Asumimos que hay un método que convierte una lista de viandas DTO en viandas

    distribucion.setMotivoDistribucion(inputDTO.getMotivoDistribucion());

    return distribucion;
  }

  public static DistribucionOutputDTO convertirDistribucionEnDTO(DistribucionVianda distribucion) {
    DistribucionOutputDTO outputDTO = new DistribucionOutputDTO();

    outputDTO.setHeladeraOrigen(distribucion.getHeladeraOrigen());
    outputDTO.setHeladeraDestino(distribucion.getHeladeraDestino());
    outputDTO.setFechaDistribucion(distribucion.getFecha());
    outputDTO.setViandas(ViandaFactory.convertirViandasEnDTOs(distribucion.getViandas()));
    outputDTO.setMotivoDistribucion(distribucion.getMotivoDistribucion());

    return outputDTO;
  }
}


