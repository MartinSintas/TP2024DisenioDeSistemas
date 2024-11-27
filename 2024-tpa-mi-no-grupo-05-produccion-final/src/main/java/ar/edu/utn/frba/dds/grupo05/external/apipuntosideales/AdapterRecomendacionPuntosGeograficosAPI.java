package ar.edu.utn.frba.dds.grupo05.external.apipuntosideales;

import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.factories.PuntoGeograficoFactory;
import ar.edu.utn.frba.dds.grupo05.services.recomendadorpuntosgeograficos.PuntoGeograficoDTO;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class AdapterRecomendacionPuntosGeograficosAPI implements IAdapterRecomendacionPuntosGeograficosAPI {

    // Sacar de properties o env
    private static String urlBase;
    private final Retrofit retrofit;

    public AdapterRecomendacionPuntosGeograficosAPI() throws IOException {
        if (urlBase == null) {
            String path = "src/main/resources/app.properties";

            Properties appProps = new Properties();
            appProps.load(new FileInputStream(path));

            urlBase = appProps.getProperty("urlBaseGeoApiMock");
        }

        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public List<PuntoGeografico> recomendarPuntos(String latitud,
                                                  String longitud,
                                                  Double radioEnKm) throws IOException {

        ApiPuntosIdeales apiPuntosIdeales = this.retrofit.create(ApiPuntosIdeales.class);

        Call<List<PuntoGeograficoDTO>> request = apiPuntosIdeales
                .obtenerPuntosRecomendados(latitud, latitud, radioEnKm);

        Response<List<PuntoGeograficoDTO>> response = request.execute();

        if (!response.isSuccessful() || response.body() == null) {
            throw new IOException("No se pudieron obtener los puntos recomendados");
        }

        return PuntoGeograficoFactory.crearAPartirDe(response.body());
    }
}
