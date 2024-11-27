package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContactoFactory;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.CargaArchivoCsvInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.PersonaHumanaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.PersonaHumanaOutputDTO;

public class PersonaHumanaFactory {


    public static PersonaHumana crearPersonaDesdeDTO(PersonaHumanaInputDTO inputDTO) {
        PersonaHumana persona = new PersonaHumana();

        persona.setNombre(inputDTO.getNombre());
        persona.setApellido(inputDTO.getApellido());
        persona.setTipoDocumento(inputDTO.getTipoDocumento());
        persona.setNroDocumento(inputDTO.getNroDocumento());
        persona.setFechaNacimiento(inputDTO.getFechaNacimiento());
        persona.agregarMedioDeContacto(
                MedioDeContactoFactory.crearMedioDeContactoDesdeDTO(inputDTO.getMedioDeContacto())
        );
        persona.setRespuestasFormulario(inputDTO.getRespuestasFormularioDinamico());
        persona.setDireccion(new PuntoGeografico(inputDTO.getLatitudDireccion(), inputDTO.getLongitudDireccion()));
        return persona;
    }

    public static PersonaHumana crearCargaMasivaDesdeDTO(CargaArchivoCsvInputDTO inputDTO){
        PersonaHumana personaCargaMasiva = new PersonaHumana();
        personaCargaMasiva.setTipoDocumento(inputDTO.getTipoDocumento());
        personaCargaMasiva.setNroDocumento(inputDTO.getNroDocumento());
        personaCargaMasiva.setNombre(inputDTO.getNombre());
        personaCargaMasiva.setApellido(inputDTO.getApellido());
        //personaCargaMasiva.setMail(inputDTO.getMail());
        personaCargaMasiva.setFechaNacimiento(inputDTO.getFechaColaboracion());
        //personaCargaMasiva.setCantidad(inputDTO.getCantidad());

        return personaCargaMasiva;
    }


    public static PersonaHumanaOutputDTO convertirPersonaEnDTO(PersonaHumana persona) {
        PersonaHumanaOutputDTO outputDTO = new PersonaHumanaOutputDTO();

        outputDTO.setId(persona.getId());
        outputDTO.setNombre(persona.getNombre());
        outputDTO.setApellido(persona.getApellido());
        outputDTO.setTipoDocumento(persona.getTipoDocumento());
        outputDTO.setNroDocumento(persona.getNroDocumento());
        return outputDTO;
    }
}
