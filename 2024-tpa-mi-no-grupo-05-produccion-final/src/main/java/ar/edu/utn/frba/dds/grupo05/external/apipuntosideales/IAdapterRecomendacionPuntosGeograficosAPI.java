package ar.edu.utn.frba.dds.grupo05.external.apipuntosideales;

import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;

import java.io.IOException;
import java.util.List;

public interface IAdapterRecomendacionPuntosGeograficosAPI {
    List<PuntoGeografico> recomendarPuntos(String latitud, String longitud, Double radioEnKm) throws IOException;
}
