package ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CoeficientePuntos {
    private Double multiplicador;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private Boolean estaActivo;

    public CoeficientePuntos(Double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public Double multiplicar(Double base) {
        return multiplicador * base;
    }
}
