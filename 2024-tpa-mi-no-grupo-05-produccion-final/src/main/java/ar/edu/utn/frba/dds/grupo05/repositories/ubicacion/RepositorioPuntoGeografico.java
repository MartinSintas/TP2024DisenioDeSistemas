package ar.edu.utn.frba.dds.grupo05.repositories.ubicacion;

import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioPuntoGeografico extends Repositorio {

    public void guardarPuntoGeografico(PuntoGeografico puntoGeografico) {
        this.guardar(puntoGeografico);
    }

    public List<PuntoGeografico> buscarPuntoGeografico() {
        return (List<PuntoGeografico>) (List<?>) this.buscarTodos("punto_geografico");
    }

    public void actualizarPuntoGeografico(PuntoGeografico puntoGeografico) {
        this.actualizar(puntoGeografico);
    }

    public void eliminarPuntoGeografico(PuntoGeografico puntoGeografico) {
        this.eliminar(puntoGeografico);
    }
}
