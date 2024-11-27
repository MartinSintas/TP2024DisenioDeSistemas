package ar.edu.utn.frba.dds.grupo05.dtos.inputs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AperturaHeladeraInputDTO {
    private Long idApertura;
    private String estadoApertura;
    private LocalDateTime horaApertura;
}
