package ar.edu.utn.frba.dds.grupo05.repositories.zona;

import ar.edu.utn.frba.dds.grupo05.domain.entities.zona.Zona;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioZona extends Repositorio {
    public void guardarZona(Zona zona) {
        this.guardar(zona);
    }

    public List<Zona> buscarZona() {
        return (List<Zona>) (List<?>) this.buscarTodos("zona");
    }

    public void actualizarZona(Zona zona) {
        this.actualizar(zona);
    }

    public void eliminarZona(Zona zona) {
        this.eliminar(zona);
    }
}
