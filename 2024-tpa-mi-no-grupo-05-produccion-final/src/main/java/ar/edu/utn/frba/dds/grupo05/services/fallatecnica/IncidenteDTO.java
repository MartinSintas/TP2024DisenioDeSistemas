package ar.edu.utn.frba.dds.grupo05.services.fallatecnica;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class IncidenteDTO {
    private String id;
    private String estado_incidente;
    private String fecha_incidente;
    private String foto_resolucion;
    private String tipo_incidente;
    private String heladera_id;
}
