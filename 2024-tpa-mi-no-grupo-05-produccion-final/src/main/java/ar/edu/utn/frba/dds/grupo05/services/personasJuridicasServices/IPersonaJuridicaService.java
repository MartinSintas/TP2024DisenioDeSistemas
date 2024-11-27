package ar.edu.utn.frba.dds.grupo05.services.personasJuridicasServices;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaJuridicaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.PersonaJuridicaOutputDTO;

public interface IPersonaJuridicaService {
  PersonaJuridicaOutputDTO darDeAlta(PersonaJuridicaInputDTO personaJuridicaInputDTO, Usuario usuario);
  PersonaJuridicaOutputDTO modificar(Long id, PersonaJuridicaInputDTO personaJuridicaInputDTO, Usuario usuario);
  void darDeBaja(Long id, Usuario usuario);
  void aumentarPuntos(Double puntos);
}
