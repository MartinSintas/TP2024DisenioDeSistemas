package ar.edu.utn.frba.dds.grupo05.converters;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
public class TipoDocumentoConverter {
    public static TipoDocumento convertir(String tipoDocumentoStr) {
        if (tipoDocumentoStr == null || tipoDocumentoStr.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo o vacío.");
        }
        try {
            return TipoDocumento.valueOf(tipoDocumentoStr.toUpperCase()); // Convertir el string a mayúsculas
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de documento inválido: " + tipoDocumentoStr);
        }
    }
}