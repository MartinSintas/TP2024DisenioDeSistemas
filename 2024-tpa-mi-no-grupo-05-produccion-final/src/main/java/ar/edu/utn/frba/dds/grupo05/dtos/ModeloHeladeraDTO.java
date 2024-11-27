package ar.edu.utn.frba.dds.grupo05.dtos;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
@Builder
@Getter
public class ModeloHeladeraDTO {
    private String detalle;
    private Double temperaturaMaxima;
    private Double temperaturaMinima;
}
