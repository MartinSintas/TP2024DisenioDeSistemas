package ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.services.recomendadorpuntosgeograficos.PuntoGeograficoDTO;

import java.util.List;

public class PuntoGeograficoFactory {
    public static PuntoGeografico crearAPartirDe(PuntoGeograficoDTO puntoGeograficoDTO) {
        // TODO llenar el resto de atributos (Otra api?)
        return PuntoGeografico
                .builder()
                .latitud(puntoGeograficoDTO.getLatitud())
                .longitud(puntoGeograficoDTO.getLongitud())
                .build();
    }

    public static List<PuntoGeografico> crearAPartirDe(List<PuntoGeograficoDTO> puntosGeograficosDTO) {
        return puntosGeograficosDTO.stream().map(PuntoGeograficoFactory::crearAPartirDe).toList();
    }
}
