package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.converters.TipoDocumentoConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.EstadoTarjeta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.TarjetaPersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaVulnerableInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.PersonaVulnerableOutputDTO;

public class PersonaVulnerableFactory {
    public static PersonaVulnerable crearPersonaDesdeDTO(PersonaVulnerableInputDTO inputDTO) {
        PersonaVulnerable personaVulnerable = new PersonaVulnerable();

        personaVulnerable.setNombre(inputDTO.getNombre());
        personaVulnerable.setFechaNacimiento(inputDTO.getFechaNacimiento());
        personaVulnerable.setTarjeta(new TarjetaPersonaVulnerable(inputDTO.getNumeroTarjeta(), personaVulnerable, EstadoTarjeta.ACTIVA));
        personaVulnerable.setDomicilio(new PuntoGeografico(inputDTO.getLatitud(), inputDTO.getLongitud()));
        // Usar el converter
        personaVulnerable.setTipoDocumento(TipoDocumentoConverter.convertir(inputDTO.getTipoDocumento()));

        personaVulnerable.setNroDocumento(inputDTO.getNroDocumento());
        personaVulnerable.setMenoresACargo(inputDTO.getMenoresACargo());

        return personaVulnerable;
    }

    public static PersonaVulnerableOutputDTO convertirPersonaEnDTO(PersonaVulnerable personaVulnerable) {
        PersonaVulnerableOutputDTO outputDTO = new PersonaVulnerableOutputDTO();

        outputDTO.setId(personaVulnerable.getId());
        outputDTO.setNombre(personaVulnerable.getNombre());
        outputDTO.setFechaNacimiento(personaVulnerable.getFechaNacimiento());


      //el salto del topo
        if (personaVulnerable.getDomicilio() != null) {
            String domicilioString = personaVulnerable.getDomicilio().getCalle() + " " +
                    personaVulnerable.getDomicilio().getAltura();
            outputDTO.setDomicilio(domicilioString);
        }

        outputDTO.setTipoDocumento(personaVulnerable.getTipoDocumento().name()); // Suponiendo que tipoDocumento es un enum
        outputDTO.setNroDocumento(personaVulnerable.getNroDocumento());


        return outputDTO;
    }
}
