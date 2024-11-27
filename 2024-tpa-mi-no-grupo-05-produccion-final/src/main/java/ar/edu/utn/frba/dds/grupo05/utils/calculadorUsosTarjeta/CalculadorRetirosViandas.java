package ar.edu.utn.frba.dds.grupo05.utils.calculadorUsosTarjeta;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaVulnerable;

public class CalculadorRetirosViandas implements ICalculadorRetirosViandas {

    public Integer calcularMaximoRetirosViandas(PersonaVulnerable personaVulnerable) {
        Integer minimoPersona = personaVulnerable.getRetirosMinimos();
        Integer viandasPorMenores = personaVulnerable.getViandasPorMenor() * personaVulnerable.cantidadMenoresACargo();
        return minimoPersona + viandasPorMenores;
    }

}
