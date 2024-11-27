package ar.edu.utn.frba.dds.grupo05.repositories.tarjetas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.RetiroVianda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RetiroViandaRepository implements IRetiroViandaRepository {
    List<RetiroVianda> usosTarjeta;

    public RetiroViandaRepository() {
        this.usosTarjeta = new ArrayList<>();
    }

    @Override
    public List<RetiroVianda> buscarPorTarjetaYFecha(String nroTarjeta, LocalDate fecha) {
        return this.usosTarjeta.stream()
                                .filter(usoTarjeta -> usoTarjeta.getCodigoTarjetaUsada().equals(nroTarjeta) &&
                                                      usoTarjeta.getFecha().toLocalDate().equals(fecha))
                                .toList();
    }
}
