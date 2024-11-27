package ar.edu.utn.frba.dds.grupo05.repositories.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.Alerta;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioAlerta extends Repositorio {

    public void guardarAlerta(Alerta alerta) {
        this.guardar(alerta);
    }

    public List<Alerta> buscarAlerta() {
        return (List<Alerta>) (List<?>) this.buscarTodos("alerta");
    }

    public void actualizarAlerta(Alerta alerta) {
        this.actualizar(alerta);
    }

    public void eliminarAlerta(Alerta alerta) {
        this.eliminar(alerta);
    }
}
