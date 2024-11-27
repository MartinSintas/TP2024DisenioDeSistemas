package ar.edu.utn.frba.dds.grupo05.dtos.inputs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViandaInputDTO {
    private String comidaDetalle;
    private LocalDate fechaCaducidad; // Cambié LocalDate a LocalDateTime
    private Integer pesoVianda; // Cambié el tipo de cantidad a pesoVianda
    private Integer calorias;
    private Boolean fueEntregada;
}
