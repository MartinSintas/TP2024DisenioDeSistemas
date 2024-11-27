package ar.edu.utn.frba.dds.grupo05.repositories.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.EstadoHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioHeladera extends Repositorio {

    public List<Heladera> buscarHeladera() {

        List<Object> resultado = buscarTodos("Heladera");
        // Convierte el resultado de Object a List<Heladera>
        return resultado.stream()
            .filter(Heladera.class::isInstance)
            .map(Heladera.class::cast)
            .collect(Collectors.toList());
    }

    public List<Heladera> heladerasPorEstado(EstadoHeladera estado) {
        TypedQuery<Heladera> query = entityManager().createQuery(
            "SELECT h FROM Heladera h WHERE h.estadoHeladera = :estado", Heladera.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    public void actualizarHeladera(Heladera heladera) {
        this.actualizar(heladera);
    }

    public void eliminarHeladera(Heladera heladera) {
        this.eliminar(heladera);
    }

    public List<Heladera> buscarHeladeraPorNombre(String nombre) {
        TypedQuery<Heladera> query = entityManager().createQuery(
            "SELECT h FROM Heladera h WHERE h.nombreSignificativo = :nombre", Heladera.class);
        query.setParameter("nombre", nombre);
        return query.getResultList();
    }

    public Optional<Heladera> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(Heladera.class, id));
    }
}
