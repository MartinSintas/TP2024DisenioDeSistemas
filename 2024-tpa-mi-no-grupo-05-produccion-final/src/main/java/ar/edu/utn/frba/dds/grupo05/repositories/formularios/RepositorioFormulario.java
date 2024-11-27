package ar.edu.utn.frba.dds.grupo05.repositories.formularios;

import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.Enunciado;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.Formulario;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.TipoFormulario;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class RepositorioFormulario extends Repositorio {

    public void guardarFormulario(Formulario formulario) {
        this.guardar(formulario);
    }

    public List<Formulario> buscarFormulario() {
        return (List<Formulario>) (List<?>) this.buscarTodos("formulario");
    }

    public Optional<Formulario> buscarPorTipoFormulario(TipoFormulario tipoFormulario) {
        try {
            String query = "SELECT f FROM Formulario f WHERE f.tipoFormulario = :tipoFormulario";
            return Optional.of(entityManager().createQuery(query, Formulario.class)
                    .setParameter("tipoFormulario", tipoFormulario)
                    .getSingleResult());
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void actualizarFormulario(Formulario formulario) {
        this.actualizar(formulario);
    }

    public void eliminarFormulario(Formulario formulario) {
        this.eliminar(formulario);
    }
}
