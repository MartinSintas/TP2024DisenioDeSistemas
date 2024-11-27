package ar.edu.utn.frba.dds.grupo05.repositories.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.MovimientoHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioMovimientoHeladera extends Repositorio {

    public void guardarMovimientoHeladera(MovimientoHeladera movimientoHeladera) {
        this.guardar(movimientoHeladera);
    }

    public List<MovimientoHeladera> buscarMovimientoHeladera() {
        return (List<MovimientoHeladera>) (List<?>) this.buscarTodos("movimiento_heladera");
    }

    public void actualizarMovimientoHeladera(MovimientoHeladera movimientoHeladera) {
        this.actualizar(movimientoHeladera);
    }

    public void eliminarHeladera(MovimientoHeladera movimientoHeladera) {
        this.eliminar(movimientoHeladera);
    }
}
