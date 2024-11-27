package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.converters.TipoContactoAStringConverter;
import ar.edu.utn.frba.dds.grupo05.converters.TipoDocumentoConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.TecnicoInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.TecnicoOutputDTO;

import java.util.Optional;

public class TecnicoFactory {

    public static Tecnico asignarValoresATecnico(TecnicoInputDTO tecnicoInputDTO){

        Tecnico nuevoTecnico = new Tecnico();
        nuevoTecnico.setNombre(tecnicoInputDTO.getNombre());
        nuevoTecnico.setApellido(tecnicoInputDTO.getApellido());
        nuevoTecnico.setUsuario(tecnicoInputDTO.getUsuario());
        nuevoTecnico.setContrasenia(tecnicoInputDTO.getContrasenia());
        nuevoTecnico.setTipoDocumento(TipoDocumentoConverter.convertir(tecnicoInputDTO.getTipoDocumento()));
        nuevoTecnico.setCuil(tecnicoInputDTO.getCuil());
        /*nuevoTecnico.setearMedioDeContacto(
                TipoContactoConverter.convertir(tecnicoInputDTO.getTipoContacto()),
                tecnicoInputDTO.getValorContacto());*/
        return nuevoTecnico;
    }

    public static TecnicoOutputDTO convertirTecnicoEnOutputDTO(Tecnico nuevoTecnico){
        TecnicoOutputDTO output = new TecnicoOutputDTO();

        output.setNombre(nuevoTecnico.getNombre());
        output.setApellido(nuevoTecnico.getApellido());
        output.setTipoDocumento(nuevoTecnico.getTipoDocumento());
        output.setCuil(nuevoTecnico.getCuil());
        output.setTipoContacto(TipoContactoAStringConverter.convertir(nuevoTecnico.getTipoContacto()));
        output.setValorContacto(nuevoTecnico.getValorContacto());
        output.setLocalidad(nuevoTecnico.getNombreLocalidad());
        output.setId(nuevoTecnico.getId());
        return output;
    }
}
