package ar.edu.utn.frba.dds.grupo05.repositories.formularios;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.FormaDeColaborar;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.Enunciado;

import java.util.List;

public class RepositorioEnunciado extends Repositorio {

    public void guardarEnunciado(Enunciado enunciado) {
        this.guardar(enunciado);
    }

    public List<Enunciado> buscarEnunciado() {
        return (List<Enunciado>) (List<?>) this.buscarTodos("enunciado");
    }

    public void actualizarEnunciado(Enunciado enunciado) {
        this.actualizar(enunciado);
    }

    public void eliminarEnunciado(Enunciado enunciado) {
        this.eliminar(enunciado);
    }
}
