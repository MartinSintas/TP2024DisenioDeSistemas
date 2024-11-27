package ar.edu.utn.frba.dds.grupo05.services.verificadorRetirosViandas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.EstadoTarjeta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.TarjetaPersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.RetiroVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.IRetiroViandaRepository;
import ar.edu.utn.frba.dds.grupo05.utils.calculadorUsosTarjeta.ICalculadorRetirosViandas;

import java.time.LocalDate;
import java.util.List;

public class VerificadorRetirosViandas {

    private IRetiroViandaRepository retiroViandaRepository;
    private ICalculadorRetirosViandas calculadorRetirosViandas;

    public VerificadorRetirosViandas(IRetiroViandaRepository retiroViandaRepository,
                                     ICalculadorRetirosViandas calculadorRetirosViandas) {
        this.retiroViandaRepository = retiroViandaRepository;
        this.calculadorRetirosViandas = calculadorRetirosViandas;
    }

    public Boolean puedeRetirarVianda(TarjetaPersonaVulnerable tarjeta, LocalDate fecha) {
        PersonaVulnerable personaVulnerable = tarjeta.getOwner();
        Integer maximoUsos = calculadorRetirosViandas.calcularMaximoRetirosViandas(personaVulnerable);

        List<RetiroVianda> usosDelDia = retiroViandaRepository.buscarPorTarjetaYFecha(tarjeta.getCodigoTarjeta(), fecha);

        return usosDelDia.size() < maximoUsos && tarjeta.getEstadoTarjeta() == EstadoTarjeta.ACTIVA;
    }
}
