package ar.edu.utn.frba.dds.grupo05.dtos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CanjeDTO {
    private Long ofertaId;
    private Double puntosUtilizados;
}
