package ar.edu.utn.frba.dds.grupo05.dtos.outputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistribucionOutputDTO {

  private Heladera heladeraOrigen;
  private Heladera heladeraDestino;
  private LocalDateTime fechaDistribucion;
  private List<ViandaOutputDTO> viandas; // Asegúrate de que ViandaOutputDTO esté definido
  private String motivoDistribucion;


}

