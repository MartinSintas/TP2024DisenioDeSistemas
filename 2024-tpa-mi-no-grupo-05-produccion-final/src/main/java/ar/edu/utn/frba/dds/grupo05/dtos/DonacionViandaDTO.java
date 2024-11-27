package ar.edu.utn.frba.dds.grupo05.dtos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DonacionViandaDTO {
    private Long heladeraId;
    private String descripcion;
    private LocalDate fechaDeCaducidad;
    private Usuario usuario;
    private Integer kiloCalorias;
    private Integer pesoEnGramos;
}
