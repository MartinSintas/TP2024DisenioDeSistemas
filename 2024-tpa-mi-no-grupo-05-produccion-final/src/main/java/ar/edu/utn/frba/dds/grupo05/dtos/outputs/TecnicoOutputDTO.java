package ar.edu.utn.frba.dds.grupo05.dtos.outputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoOutputDTO {
  private String nombre;
  private String apellido;
  private TipoDocumento tipoDocumento;
  private String cuil;
  private String tipoContacto;
  private String valorContacto;
  private String localidad;
  private PuntoGeografico ubicacion;
  private Long id;
}
