package ar.edu.utn.frba.dds.grupo05.repositories.personas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonaHumanaListRepository implements IPersonaHumanaRepository {
    List<PersonaHumana> personas;

    public PersonaHumanaListRepository() {
        this.personas = new ArrayList<>();
    }

    @Override
    public List<PersonaHumana> bulkSave(List<PersonaHumana> personas) {
        this.personas.addAll(personas);
        return personas;
    }

    @Override
    public List<PersonaHumana> getAll() {
        return this.personas;
    }

    @Override
    public Object existsByTipoDocumentoAndDocumento(TipoDocumento any, String any1) {
        return null;
    }

    @Override
    public Optional<PersonaHumana> buscarPorId(Long id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public void save(PersonaHumana personaHumana) {
        this.personas.add(personaHumana);
    }
}
