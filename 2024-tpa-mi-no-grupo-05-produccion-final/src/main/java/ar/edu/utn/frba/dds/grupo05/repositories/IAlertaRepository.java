package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.Alerta;

import java.util.List;

public interface IAlertaRepository {
    void guardar(Alerta... alertas);
    List<Alerta> buscarTodas();
}
