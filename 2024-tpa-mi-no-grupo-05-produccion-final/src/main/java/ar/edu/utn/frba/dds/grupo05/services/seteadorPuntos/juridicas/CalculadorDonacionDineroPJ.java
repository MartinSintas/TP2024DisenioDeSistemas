package ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos.juridicas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos.CalculadorPuntos;

public class CalculadorDonacionDineroPJ extends CalculadorPuntos<PersonaJuridica> {
    public Double calcularBase(PersonaJuridica personaJuridica) {
        // Obtener donaciones del ultimo mes de la personaJuridica
        return personaJuridica.montoTotalDonado();
    }
}
