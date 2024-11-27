package ar.edu.utn.frba.dds.grupo05.dtos.inputs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FallaTecnicaInputDTO {
    private LocalDateTime fechaReporteDelIncidente;
    private Long idHeladera;
    private Long idColaborador;
    private String descripcion;
    private String foto;
}
