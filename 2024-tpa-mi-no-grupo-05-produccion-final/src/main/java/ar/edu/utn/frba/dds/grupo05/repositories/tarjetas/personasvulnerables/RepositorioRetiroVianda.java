package ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.personasvulnerables;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.RetiroVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioRetiroVianda extends Repositorio {

    public void guardarRetiroVianda(RetiroVianda retiroVianda) {
        this.guardar(retiroVianda);
    }

    public List<RetiroVianda> buscarTodosLosRetiroVianda() {
        return (List<RetiroVianda>) (List<?>) this.buscarTodos("retiro_vianda");
    }

    public void actualizarRetiroVianda(RetiroVianda retiroVianda) {
        this.actualizar(retiroVianda);
    }

    public void eliminarRetiroVianda(RetiroVianda retiroVianda) {
        this.eliminar(retiroVianda);
    }
}
