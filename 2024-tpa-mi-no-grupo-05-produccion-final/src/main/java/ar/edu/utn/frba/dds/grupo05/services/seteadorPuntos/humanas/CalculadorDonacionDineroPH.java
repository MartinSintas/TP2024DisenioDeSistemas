package ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos.humanas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos.CalculadorPuntos;

public class CalculadorDonacionDineroPH extends CalculadorPuntos<PersonaHumana> {
    public Double calcularBase(PersonaHumana personaHumana) {
        // Obtener donaciones del ultimo mes de la personaJuridica
        return personaHumana.montoTotalDonado();
    }
}
