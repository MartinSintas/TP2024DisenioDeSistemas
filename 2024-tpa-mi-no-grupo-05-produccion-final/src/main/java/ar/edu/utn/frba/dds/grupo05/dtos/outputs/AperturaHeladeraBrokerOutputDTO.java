package ar.edu.utn.frba.dds.grupo05.dtos.outputs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AperturaHeladeraBrokerOutputDTO {
    private Long idHeladera;
    private Long idAperturaHeladera;
    private String codigoTarjeta;
    private LocalDateTime horaLimite;
}
