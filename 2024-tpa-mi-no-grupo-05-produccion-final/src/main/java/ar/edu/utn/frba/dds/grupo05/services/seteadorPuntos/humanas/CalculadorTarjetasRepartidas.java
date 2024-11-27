package ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos.humanas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos.CalculadorPuntos;

public class CalculadorTarjetasRepartidas extends CalculadorPuntos<PersonaHumana> {
    @Override
    public Double calcularBase(PersonaHumana personaHumana) {
        return personaHumana.cantTarjetasRepartidas().doubleValue();
    }
}
