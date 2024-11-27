package ar.edu.utn.frba.dds.grupo05.services.recomendadorpuntosgeograficos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.EstadoHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.grupo05.external.apipuntosideales.IAdapterRecomendacionPuntosGeograficosAPI;
import ar.edu.utn.frba.dds.grupo05.services.heladera.IHeladeraService;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Setter
@Getter
public class RecomendadorPuntosGeograficos {
    private IAdapterRecomendacionPuntosGeograficosAPI servicioRecomendadorPuntosGeograficos;
    // Sacar de properties o env

    public RecomendadorPuntosGeograficos(IAdapterRecomendacionPuntosGeograficosAPI servicioRecomendadorPuntosGeograficos) {
        this.servicioRecomendadorPuntosGeograficos = servicioRecomendadorPuntosGeograficos;
    }

    public List<PuntoGeografico> recomendarPuntos(PuntoGeografico puntoGeografico, Double radioEnKm) throws IOException {
        return this.servicioRecomendadorPuntosGeograficos
                .recomendarPuntos(puntoGeografico.getLatitud(), puntoGeografico.getLongitud(), radioEnKm);

    }

    public List<PuntoGeografico> recomendarPuntosGeograficos(){
        return null;
    }
}
