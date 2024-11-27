package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaJuridicaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.PersonaJuridicaOutputDTO;

public class PersonaJuridicaFactory {
  public static PersonaJuridica asignarValoresAPersonaJuridica(PersonaJuridicaInputDTO personaJuridicaInputDTO) {
    PersonaJuridica nuevaPersonaJuridica = new PersonaJuridica();
    nuevaPersonaJuridica.setRazonSocial(personaJuridicaInputDTO.getRazonSocial());
    nuevaPersonaJuridica.setTipoJuridico(personaJuridicaInputDTO.getTipo());
    nuevaPersonaJuridica.setRubro(personaJuridicaInputDTO.getRubro());
    nuevaPersonaJuridica.setMediosDeContacto(personaJuridicaInputDTO.getMediosDeContacto());
    return nuevaPersonaJuridica;
  }

  // Método para convertir una instancia de PersonaJuridica en un PersonaJuridicaOutputDTO
  public static PersonaJuridicaOutputDTO convertirAPersonaJuridicaOutputDTO(PersonaJuridica nuevaPersonaJuridica) {
    PersonaJuridicaOutputDTO output = new PersonaJuridicaOutputDTO();
    output.setRazonSocial(nuevaPersonaJuridica.getRazonSocial());
    output.setTipo(nuevaPersonaJuridica.getTipoJuridico());
    output.setRubro(nuevaPersonaJuridica.getRubro());
    output.setMediosDeContacto(nuevaPersonaJuridica.getMediosDeContacto());
    output.setId(nuevaPersonaJuridica.getId());
    return output;
  }
}
