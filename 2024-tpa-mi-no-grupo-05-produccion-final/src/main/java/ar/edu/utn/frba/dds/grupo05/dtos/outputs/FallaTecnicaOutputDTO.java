package ar.edu.utn.frba.dds.grupo05.dtos.outputs;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FallaTecnicaOutputDTO {
    private Long id;
    private Long idHeladera;
    private Long idColaborador;
    private LocalDateTime fechaReporteDelIncidente;
    private String descripcion;
    private String foto;
}
