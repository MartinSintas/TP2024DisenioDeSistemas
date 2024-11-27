package ar.edu.utn.frba.dds.grupo05.services.recomendadorpuntosgeograficos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PuntoGeograficoDTO {
    public String latitud;
    public String longitud;

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }
}
