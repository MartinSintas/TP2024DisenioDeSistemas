package ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos.juridicas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.services.seteadorPuntos.CalculadorPuntos;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalculadorHeladerasActivas extends CalculadorPuntos<PersonaJuridica> {
    public Double calcularBase(PersonaJuridica personaJuridica) {
        Double cantHeladerasActivas = personaJuridica.cantHeladerasActivas().doubleValue();
        Double cantMesesActivas = personaJuridica.mesesTotalesHeladerasActivas().doubleValue();
        return cantHeladerasActivas * cantMesesActivas;
    }
}
