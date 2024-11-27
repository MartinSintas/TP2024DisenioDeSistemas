package ar.edu.utn.frba.dds.grupo05.repositories.personas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioPersonaHumana extends Repositorio {
    public void guardarPersonaHumana(PersonaHumana personaHumana) {
        this.guardar(personaHumana);
    }

    public List<PersonaHumana> buscarPersonaHumana() {
        return (List<PersonaHumana>) (List<?>) this.buscarTodos("persona_humana");
    }

    public Optional<PersonaHumana> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(PersonaHumana.class, id));
    }

    public Optional<PersonaHumana> buscarPorUsuario(Usuario usuario) {
        entityManager().clear();
        return Optional.ofNullable(entityManager()
                .createQuery("SELECT p FROM PersonaHumana p WHERE p.usuario = :usuario", PersonaHumana.class)
                .setParameter("usuario", usuario)
                .getSingleResult());
    }
    public List<PersonaHumana> buscarTodas() {
        List<Object> resultado = buscarTodos("PersonaHumana");

        return resultado.stream()
            .filter(PersonaHumana.class::isInstance)
            .map(PersonaHumana.class::cast)
            .collect(Collectors.toList());
    }

    public void actualizarPersonaHumana(PersonaHumana personaHumana) {
        this.actualizar(personaHumana);
    }

    public void eliminarPersonaHumana(PersonaHumana personaHumana) {
        this.eliminar(personaHumana);
    }
}
