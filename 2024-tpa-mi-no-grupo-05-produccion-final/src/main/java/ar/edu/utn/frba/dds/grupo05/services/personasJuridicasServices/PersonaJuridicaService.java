package ar.edu.utn.frba.dds.grupo05.services.personasJuridicasServices;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaJuridicaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.PersonaJuridicaOutputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.PersonaJuridicaNoEncontrada;
import ar.edu.utn.frba.dds.grupo05.factories.PersonaJuridicaFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.services.usuario.IUsuarioService;
import ar.edu.utn.frba.dds.grupo05.services.verificadorPermisos.VerificadorDePermisos;

import java.util.Optional;

public class PersonaJuridicaService implements IPersonaJuridicaService {
  private RepositorioPersonaJuridica personaJuridicaRepository;
  private IUsuarioService usuarioService; // Asegúrate de que esta clase exista

  public PersonaJuridicaService(RepositorioPersonaJuridica personaJuridicaRepository,
                                IUsuarioService usuarioService) {
    this.personaJuridicaRepository = personaJuridicaRepository;
    this.usuarioService = usuarioService;
  }

  @Override
  public PersonaJuridicaOutputDTO darDeAlta(PersonaJuridicaInputDTO personaJuridicaInputDTO, Usuario usuario) {
    PersonaJuridica nuevaPersonaJuridica = PersonaJuridicaFactory.asignarValoresAPersonaJuridica(personaJuridicaInputDTO);

    this.personaJuridicaRepository.guardar(nuevaPersonaJuridica);
    return PersonaJuridicaFactory.convertirAPersonaJuridicaOutputDTO(nuevaPersonaJuridica);
  }
  @Override
  public PersonaJuridicaOutputDTO modificar(Long id, PersonaJuridicaInputDTO personaJuridicaInputDTO, Usuario usuario) {

    Optional<PersonaJuridica> posiblePersonaJuridica = this.personaJuridicaRepository.buscarPorId(id);

    if (posiblePersonaJuridica.isEmpty()) {
      throw new PersonaJuridicaNoEncontrada("No se encontró la persona jurídica con id: " + id + " para modificar.");
    }

    PersonaJuridica personaJuridicaAModificar = posiblePersonaJuridica.get();

    // Asignar valores desde el DTO a la entidad existente
    personaJuridicaAModificar.setRazonSocial(personaJuridicaInputDTO.getRazonSocial());
    personaJuridicaAModificar.setTipoJuridico(personaJuridicaInputDTO.getTipo());
    personaJuridicaAModificar.setRubro(personaJuridicaInputDTO.getRubro());
    personaJuridicaAModificar.setMediosDeContacto(personaJuridicaInputDTO.getMediosDeContacto());

    this.personaJuridicaRepository.actualizar(personaJuridicaAModificar);

    return PersonaJuridicaFactory.convertirAPersonaJuridicaOutputDTO(personaJuridicaAModificar);
  }
  @Override
  public void darDeBaja(Long id, Usuario usuario) {

    Optional<PersonaJuridica> posiblePersonaJuridica = this.personaJuridicaRepository.buscarPorId(id);

    if (posiblePersonaJuridica.isEmpty()) {
      throw new PersonaJuridicaNoEncontrada("No se encontró la persona jurídica con id: " + id + " para dar de baja.");
    }

    PersonaJuridica personaJuridicaAEliminar = posiblePersonaJuridica.get();

    this.personaJuridicaRepository.eliminar(personaJuridicaAEliminar);
  }

  @Override
  public void aumentarPuntos(Double puntos) {

  }


}
