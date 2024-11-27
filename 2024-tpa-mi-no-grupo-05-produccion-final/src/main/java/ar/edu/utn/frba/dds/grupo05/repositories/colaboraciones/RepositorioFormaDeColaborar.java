package ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.FormaDeColaborar;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioFormaDeColaborar extends Repositorio {
    public void guardarFormaDeColaborar(FormaDeColaborar formaDeColaborar) {
        this.guardar(formaDeColaborar);
    }

    public List<FormaDeColaborar> buscarFormaDeColaborar() {
        return (List<FormaDeColaborar>) (List<?>) this.buscarTodos("forma_de_colaboracion");
    }

    public void actualizarFormaDeColaborar(FormaDeColaborar formaDeColaborar) {
        this.actualizar(formaDeColaborar);
    }

    public void eliminarFormaDeColaborar(FormaDeColaborar formaDeColaborar) {
        this.eliminar(formaDeColaborar);
    }
}
