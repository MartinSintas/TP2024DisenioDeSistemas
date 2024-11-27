package ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.solicitudesApertura.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.AperturaHeladera;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.AperturaHeladeraBrokerOutputDTO;

public class AperturaHeladeraFactory {

  public static AperturaHeladeraBrokerOutputDTO toBrokerDTO(AperturaHeladera aperturaHeladera) {
    AperturaHeladeraBrokerOutputDTO dto = new AperturaHeladeraBrokerOutputDTO();
    dto.setIdHeladera(aperturaHeladera.getHeladera().getId());
    dto.setIdAperturaHeladera(aperturaHeladera.getId());
    dto.setCodigoTarjeta(aperturaHeladera.getTarjeta().getCodigoTarjeta());
    dto.setHoraLimite(aperturaHeladera.getHoraLimite());
    return dto;
  }
}
