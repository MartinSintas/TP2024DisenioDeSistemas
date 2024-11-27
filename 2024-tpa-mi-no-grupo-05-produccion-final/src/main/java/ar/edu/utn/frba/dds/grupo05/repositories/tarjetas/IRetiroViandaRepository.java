package ar.edu.utn.frba.dds.grupo05.repositories.tarjetas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.RetiroVianda;

import java.time.LocalDate;
import java.util.List;

public interface IRetiroViandaRepository {
    List<RetiroVianda> buscarPorTarjetaYFecha(String nroTarjeta, LocalDate fecha);
}
