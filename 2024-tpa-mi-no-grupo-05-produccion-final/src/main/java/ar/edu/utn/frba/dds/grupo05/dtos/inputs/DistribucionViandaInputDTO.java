package ar.edu.utn.frba.dds.grupo05.dtos.inputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistribucionViandaInputDTO {
  private Heladera heladeraOrigenId;
  private Heladera heladeraDestinoId;
  private String motivoDistribucion;
  private LocalDateTime fechaDistribucion;
  private Integer cantidadViandas;
}

