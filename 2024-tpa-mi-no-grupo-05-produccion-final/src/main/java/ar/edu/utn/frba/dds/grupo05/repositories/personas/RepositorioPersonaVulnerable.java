package ar.edu.utn.frba.dds.grupo05.repositories.personas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class RepositorioPersonaVulnerable extends Repositorio {

    public void guardarPersonaVulnerable(PersonaVulnerable personaVulnerable) {
        this.guardar(personaVulnerable);
    }

    public List<PersonaVulnerable> buscarPersonaVulnerable() {
        return (List<PersonaVulnerable>) (List<?>) this.buscarTodos("persona_vulnerable");
    }

    public boolean existeTarjetaConCodigo(String numeroTarjeta) {
        String jpql = "SELECT COUNT(t) FROM TarjetaPersonaVulnerable t WHERE t.codigoTarjeta = :numeroTarjeta";
        TypedQuery<Long> query = entityManager().createQuery(jpql, Long.class);
        query.setParameter("numeroTarjeta", numeroTarjeta);
        Long count = query.getSingleResult();
        return count > 0;
    }


    public Optional<PersonaVulnerable> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(PersonaVulnerable.class, id));
    }

    public void actualizarPersonaVulnerable(PersonaVulnerable personaVulnerable) {
        this.actualizar(personaVulnerable);
    }

    public void eliminarPersonaVulnerable(PersonaVulnerable personaVulnerable) {
        this.eliminar(personaVulnerable);
    }
}
