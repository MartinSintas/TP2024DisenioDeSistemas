package ar.edu.utn.frba.dds.grupo05.repositories.personas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.Rubro;
import ar.edu.utn.frba.dds.grupo05.exceptions.DistribucionViandaNotFoundException;
import ar.edu.utn.frba.dds.grupo05.exceptions.RubroNotFoundException;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class RepositorioRubro extends Repositorio {
    public void guardarRubro(Rubro rubro) {
        this.guardar(rubro);
    }

    public List<Rubro> buscarRubros() {
        List<Rubro> rubros = this.buscarTodosPorClase(Rubro.class);

        if (rubros.isEmpty()) {
            return null;
        }
        return rubros;
    }

    public Optional<Rubro> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(Rubro.class, id));
    }

    public void actualizarRubro(Rubro rubro) {
        this.actualizar(rubro);
    }

    public Optional<Rubro> buscarPorNombre(String nombre) {
        TypedQuery<Rubro> query = entityManager()
            .createQuery("SELECT r FROM Rubro r WHERE r.nombre = :nombre", Rubro.class);
        query.setParameter("nombre", nombre);

        return query.getResultStream().findFirst();
    }

    public void eliminarRubro(Rubro rubro) {
        this.eliminar(rubro);
    }
}
