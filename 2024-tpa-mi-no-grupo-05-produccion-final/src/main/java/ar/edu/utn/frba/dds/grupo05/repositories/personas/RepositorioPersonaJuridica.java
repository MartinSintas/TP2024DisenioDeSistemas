package ar.edu.utn.frba.dds.grupo05.repositories.personas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioPersonaJuridica extends Repositorio implements IRepositorioPersonasJuridicas{

    public void guardarPersonaJuridica(PersonaJuridica personaJuridica) {
        this.guardar(personaJuridica);
    }

    public List<PersonaJuridica> buscarPersonaJuridica() {
        List<Object> resultado = buscarTodos("PersonaJuridica");

        return resultado.stream()
            .filter(PersonaJuridica.class::isInstance)
            .map(PersonaJuridica.class::cast)
            .collect(Collectors.toList());
    }

    public Optional<PersonaJuridica> buscarPorUsuario(Usuario usuario) {
        entityManager().clear();
        return Optional.ofNullable(entityManager()
                .createQuery("SELECT p FROM PersonaJuridica p WHERE p.usuario = :usuario", PersonaJuridica.class)
                .setParameter("usuario", usuario)
                .getSingleResult());
    }

    public Optional<PersonaJuridica> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(PersonaJuridica.class, id));
    }
    public void actualizarPersonaJuridica(PersonaJuridica personaJuridica) {
        this.actualizar(personaJuridica);
    }

    public void eliminarPersonaJuridica(PersonaJuridica personaJuridica) {
        this.eliminar(personaJuridica);
    }
}
