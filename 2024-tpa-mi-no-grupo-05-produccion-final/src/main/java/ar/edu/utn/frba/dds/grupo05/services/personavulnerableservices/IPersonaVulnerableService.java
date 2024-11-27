package ar.edu.utn.frba.dds.grupo05.services.personavulnerableservices;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaVulnerableInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.PersonaVulnerableOutputDTO;

public interface IPersonaVulnerableService {
    void darDeAlta(PersonaVulnerableInputDTO personaVulnerableInputDTO);
    PersonaVulnerableOutputDTO modificar(Long id, PersonaVulnerableInputDTO personaVulnerableInputDTO, Usuario usuario);
    void darDeBaja(Long id, Usuario usuario);
}
