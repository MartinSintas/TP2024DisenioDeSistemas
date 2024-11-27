package ar.edu.utn.frba.dds.grupo05.repositories.personas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;

import java.util.List;
import java.util.Optional;

public interface IRepositorioPersonasJuridicas {
  void guardarPersonaJuridica(PersonaJuridica personaJuridica);
  List<PersonaJuridica> buscarPersonaJuridica();
  Optional<PersonaJuridica> buscarPorId(Long id);
  void actualizarPersonaJuridica(PersonaJuridica personaJuridica);
  void eliminarPersonaJuridica(PersonaJuridica personaJuridica);
}
