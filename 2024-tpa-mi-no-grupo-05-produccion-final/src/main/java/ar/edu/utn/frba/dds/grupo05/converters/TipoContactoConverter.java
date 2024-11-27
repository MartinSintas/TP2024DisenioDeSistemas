package ar.edu.utn.frba.dds.grupo05.converters;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.TipoContacto;

public class TipoContactoConverter {
    public static TipoContacto convertir(String tipoContactoStr) {
        if (tipoContactoStr == null || tipoContactoStr.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de contacto no puede ser nulo o vacío.");
        }
        try {
            return TipoContacto.valueOf(tipoContactoStr.toUpperCase()); // Convertir el string a mayúsculas
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de contacto inválido: " + tipoContactoStr);
        }
    }
}
