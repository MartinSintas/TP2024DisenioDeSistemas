package ar.edu.utn.frba.dds.grupo05.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LocalidadDTO {
    private String nombre;
    private String provincia;
}
