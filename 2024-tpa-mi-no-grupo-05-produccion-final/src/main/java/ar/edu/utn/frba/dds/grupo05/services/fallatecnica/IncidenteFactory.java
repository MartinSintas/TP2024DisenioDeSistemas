package ar.edu.utn.frba.dds.grupo05.services.fallatecnica;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.Incidente;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.grupo05.factories.HeladeraFactory;

import java.util.List;

public class IncidenteFactory {

    public static IncidenteDTO fromEntity(Incidente incidente) {
        return IncidenteDTO.builder()
                .id(String.valueOf(incidente.getId()))
                .estado_incidente(String.valueOf(incidente.getEstadoIncidente()))
                .fecha_incidente(String.valueOf(incidente.getFechaIncidente()))
                .foto_resolucion(incidente.getFotoResolucion())
                .tipo_incidente(String.valueOf(incidente.getTipoIncidente()))
                .build();
    }

    public static List<IncidenteDTO> fromEntity(List<Incidente> incidentes) {
        return incidentes.stream().map(IncidenteFactory::fromEntity).toList();
    }
}
