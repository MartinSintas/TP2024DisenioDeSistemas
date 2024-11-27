package ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.AcumuladorPuntos;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SeteadorPuntos {
    // Método genérico para sumar puntos
    public <T extends AcumuladorPuntos> void setearPuntos(T persona, List<CalculadorPuntos<T>> calculadores) {
        Double puntosTotales = calculadores
                .stream()
                .mapToDouble(c -> c.calcularPuntos(persona))
                .sum();

        puntosTotales -= persona.getPuntosConsumidos(); // Puede ser tambien un "Calculador" con coeficiente -1

        persona.setPuntosAcumulados(puntosTotales);
    }
}
