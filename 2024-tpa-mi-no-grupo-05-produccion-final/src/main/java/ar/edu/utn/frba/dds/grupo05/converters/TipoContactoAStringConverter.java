package ar.edu.utn.frba.dds.grupo05.converters;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.TipoContacto;

public class TipoContactoAStringConverter {
    public static String convertir(TipoContacto tipoContacto) {
        if (tipoContacto == null) {
            throw new IllegalArgumentException("El tipo de contacto no puede ser nulo.");
        }
        return tipoContacto.name().toLowerCase(); // Devuelve el nombre en minúsculas
    }
}
