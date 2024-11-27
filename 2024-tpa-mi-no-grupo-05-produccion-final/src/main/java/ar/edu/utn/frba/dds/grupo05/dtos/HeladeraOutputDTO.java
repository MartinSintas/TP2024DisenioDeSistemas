package ar.edu.utn.frba.dds.grupo05.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class HeladeraOutputDTO {
  private Long id;
  private String status;
  private Integer currentMeals;

  private String name;
  private Integer capacity;
  private Long model;
  private String longitude;
  private String latitude;
  private String direccion;
  private String descripcion;
  private String localidad;
}
