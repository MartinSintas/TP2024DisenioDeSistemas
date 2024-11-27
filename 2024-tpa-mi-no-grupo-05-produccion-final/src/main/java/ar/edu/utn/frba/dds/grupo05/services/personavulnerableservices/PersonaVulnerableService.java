package ar.edu.utn.frba.dds.grupo05.services.personavulnerableservices;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaVulnerableInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.PersonaVulnerableOutputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.CodigoTarjetaYaExisteException;
import ar.edu.utn.frba.dds.grupo05.factories.PersonaVulnerableFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.services.verificadorPermisos.VerificadorDePermisos;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class PersonaVulnerableService implements IPersonaVulnerableService {

    private final RepositorioPersonaVulnerable personaVulnerableRepository;
    private final VerificadorDePermisos verificadorDePermisos;

    public PersonaVulnerableService(RepositorioPersonaVulnerable personaVulnerableRepository, VerificadorDePermisos verificadorDePermisoss) {
        this.personaVulnerableRepository = personaVulnerableRepository;
        this.verificadorDePermisos = verificadorDePermisoss;
    }

    @Override
    @Transactional
    public void darDeAlta(PersonaVulnerableInputDTO personaVulnerableInputDTO) {
        if (personaVulnerableRepository.existeTarjetaConCodigo(personaVulnerableInputDTO.getNumeroTarjeta())) {
            throw new CodigoTarjetaYaExisteException("El código de tarjeta ya existe: " + personaVulnerableInputDTO.getNumeroTarjeta());
        }

        PersonaVulnerable nuevaPersonaVulnerable = PersonaVulnerableFactory.crearPersonaDesdeDTO(personaVulnerableInputDTO);
        try {
            personaVulnerableRepository.guardar(nuevaPersonaVulnerable);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar Persona Vulnerable.", e); // Opcional: lanzar otra excepción
        }
    }


    @Override
    public PersonaVulnerableOutputDTO modificar(Long id, PersonaVulnerableInputDTO personaVulnerableInputDTO, Usuario usuario) {

        Optional<PersonaVulnerable> posiblePersonaVulnerable = this.personaVulnerableRepository.buscarPorId(id);
        if (posiblePersonaVulnerable.isPresent()) {
            PersonaVulnerable personaVulnerableAModificar = posiblePersonaVulnerable.get();


            this.personaVulnerableRepository.actualizar(personaVulnerableAModificar);
            return PersonaVulnerableFactory.convertirPersonaEnDTO(personaVulnerableAModificar);
        } else {
            throw new RuntimeException("Persona vulnerable no encontrada.");
        }
    }

    @Override
    public void darDeBaja(Long id, Usuario usuario) {


        Optional<PersonaVulnerable> posiblePersonaVulnerable = this.personaVulnerableRepository.buscarPorId(id);
        if (posiblePersonaVulnerable.isPresent()) {
            PersonaVulnerable personaVulnerableAEliminar = posiblePersonaVulnerable.get();
            this.personaVulnerableRepository.eliminar(personaVulnerableAEliminar);
        } else {
            throw new RuntimeException("Persona vulnerable no encontrada.");
        }
    }

    public Optional<PersonaVulnerable> buscarPorId(Long id) {
        return this.personaVulnerableRepository.buscarPorId(id);
    }

}
