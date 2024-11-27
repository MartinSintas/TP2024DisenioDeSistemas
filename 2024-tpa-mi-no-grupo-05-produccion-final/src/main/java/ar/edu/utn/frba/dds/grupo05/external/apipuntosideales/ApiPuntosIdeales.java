package ar.edu.utn.frba.dds.grupo05.external.apipuntosideales;

import ar.edu.utn.frba.dds.grupo05.services.recomendadorpuntosgeograficos.PuntoGeograficoDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ApiPuntosIdeales {
    @GET("puntosrecomendados")
    Call<List<PuntoGeograficoDTO>> obtenerPuntosRecomendados(@Query("lat") String latitud,
                                                             @Query("lng") String longitud,
                                                             @Query("radio") Double radioEnKm);
}
