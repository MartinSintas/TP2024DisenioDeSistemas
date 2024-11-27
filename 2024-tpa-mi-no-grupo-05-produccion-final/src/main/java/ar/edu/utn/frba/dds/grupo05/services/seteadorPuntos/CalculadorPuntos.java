package ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CalculadorPuntos<T>{
    private CoeficientePuntos coeficiente;
    public abstract Double calcularBase(T persona);

    public Double calcularPuntos(T persona) {
        return this.coeficiente.multiplicar(this.calcularBase(persona));
    }
}
