package ar.edu.utn.frba.dds.grupo05.repositories.tecnico;

import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.VisitaHeladera;

import java.util.List;

public class RepositorioVisitaHeladera extends Repositorio {

    public void guardarVisitaHeladera(VisitaHeladera visitaHeladera) {
        this.guardar(visitaHeladera);
    }

    public List<VisitaHeladera> buscarVisitaHeladera() {
        return (List<VisitaHeladera>) (List<?>) this.buscarTodos("visita_heladera");
    }

    public void actualizarVisitaHeladera(VisitaHeladera visitaHeladera) {
        this.actualizar(visitaHeladera);
    }

    public void eliminarVisitaHeladera(VisitaHeladera visitaHeladera) {
        this.eliminar(visitaHeladera);
    }
}
