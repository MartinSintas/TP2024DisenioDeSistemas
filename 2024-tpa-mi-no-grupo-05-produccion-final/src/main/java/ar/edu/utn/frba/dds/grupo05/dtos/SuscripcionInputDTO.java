package ar.edu.utn.frba.dds.grupo05.dtos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SuscripcionInputDTO {
    private Long idHeladera;
    private String tipoSuscripcion;
    private Usuario usuario;
    private Integer numeroCritico;
}
