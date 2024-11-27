package ar.edu.utn.frba.dds.grupo05.services.localidadService.imp;

import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.LocalidadDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.TecnicoInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.TecnicoOutputDTO;

import java.util.List;

public interface ILocalidadService {
    void darDeAlta(LocalidadDTO localidadDTO);
    List<Localidad> obtenerModelos();
}
