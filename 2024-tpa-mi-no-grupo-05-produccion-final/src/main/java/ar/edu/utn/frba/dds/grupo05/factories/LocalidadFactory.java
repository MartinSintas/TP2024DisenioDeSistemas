package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.dtos.LocalidadDTO;

public class LocalidadFactory {
    public static Localidad fromDTO(LocalidadDTO localidadDTO) {
        Localidad localidad = new Localidad();
            localidad.setNombre(localidadDTO.getNombre());
        return localidad;
    }
}
