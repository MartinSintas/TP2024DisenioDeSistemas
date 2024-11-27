package ar.edu.utn.frba.dds.grupo05.repositories.personas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;


import java.util.List;
import java.util.Optional;

public interface IPersonaHumanaRepository {
    public List<PersonaHumana> bulkSave(List<PersonaHumana> personas);
    public List<PersonaHumana> getAll();

    public Object existsByTipoDocumentoAndDocumento(TipoDocumento any, String any1);

    public Optional<PersonaHumana> buscarPorId(Long id);

    public void save(PersonaHumana personaHumana);
}
