package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.converters.TipoContactoConverter;
import ar.edu.utn.frba.dds.grupo05.converters.TipoDocumentoConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.TipoContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.TecnicoInputDTO;

public class FactoryTecnico {
  public static Tecnico crearAPartirDe(TecnicoInputDTO tecnicoInputDTO){
    TipoDocumento tipoDocumento =  TipoDocumentoConverter.convertir(tecnicoInputDTO.getTipoDocumento());
    TipoContacto tipoContacto = TipoContactoConverter.convertir(tecnicoInputDTO.getTipoContacto());
    Tecnico nuevoTecnico = new Tecnico();
    nuevoTecnico.setNombre(tecnicoInputDTO.getNombre());
    nuevoTecnico.setApellido(tecnicoInputDTO.getApellido());
    nuevoTecnico.setTipoDocumento(tipoDocumento);
    nuevoTecnico.setCuil(tecnicoInputDTO.getCuil());
    nuevoTecnico.setearMedioDeContacto(tipoContacto, tecnicoInputDTO.getValorContacto());
    return nuevoTecnico;
  }

}
