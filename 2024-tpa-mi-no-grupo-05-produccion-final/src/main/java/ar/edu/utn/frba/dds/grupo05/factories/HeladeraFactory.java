package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;

import java.util.List;

public class HeladeraFactory {
  public static HeladeraOutputDTO fromEntity(Heladera heladera) {
    return HeladeraOutputDTO.builder()
            .id(heladera.getId())
            .name(heladera.getNombreSignificativo())
            .latitude(heladera.getLatitud())
            .longitude(heladera.getLongitud())
            .status(heladera.getEstadoHeladera().name())
            .capacity(heladera.getCapacidadEnViandas())
            .currentMeals(heladera.cantidadViandas())
            .build();
  }

  public static Heladera fromDTO(HeladeraOutputDTO heladeraOutputDTO) {

    Heladera nuevaHeladera = new Heladera();


    nuevaHeladera.setNombreSignificativo(heladeraOutputDTO.getName());
    nuevaHeladera.setCapacidadEnViandas(heladeraOutputDTO.getCapacity());
    nuevaHeladera.setLatitud(heladeraOutputDTO.getLatitude());
    nuevaHeladera.setLongitud(heladeraOutputDTO.getLongitude());
    nuevaHeladera.setDireccion(heladeraOutputDTO.getDireccion());
    nuevaHeladera.setDescripcion(heladeraOutputDTO.getDescripcion());

    return nuevaHeladera;
  }
  public static List<HeladeraOutputDTO> fromEntity(List<Heladera> heladeras) {
    return heladeras.stream().map(HeladeraFactory::fromEntity).toList();
  }
}