package ar.edu.utn.frba.dds.grupo05.services.localidadService.imp;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.dtos.LocalidadDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.TecnicoInputDTO;
import ar.edu.utn.frba.dds.grupo05.factories.LocalidadFactory;
import ar.edu.utn.frba.dds.grupo05.factories.TecnicoFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.ubicacion.RepositorioLocalidad;

import java.util.List;

public class LocalidadService implements ILocalidadService {

    private RepositorioLocalidad repositorioLocalidad;

    public LocalidadService(RepositorioLocalidad repositorioLocalidades){
        this.repositorioLocalidad = repositorioLocalidades;
    }
    public void darDeAlta(LocalidadDTO localidadDTO) {
        try {
            Localidad localidad = LocalidadFactory.fromDTO(localidadDTO);
            this.repositorioLocalidad.guardarLocalidad(localidad);
        } catch (Exception e) {
            System.err.println("Error al guardar el técnico: " + e.getMessage());
            throw new RuntimeException("Error al dar de alta la localidad: " + localidadDTO.getNombre(), e);
        }
    }

    @Override
    public List<Localidad> obtenerModelos(){
        return this.repositorioLocalidad.buscarTodas();
    }
}
