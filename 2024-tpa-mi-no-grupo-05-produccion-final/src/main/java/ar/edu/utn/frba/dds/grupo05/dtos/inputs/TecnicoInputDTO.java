package ar.edu.utn.frba.dds.grupo05.dtos.inputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TecnicoInputDTO {
  private String nombre;
  private String apellido;
  private String usuario;
  private String contrasenia;
  private String tipoDocumento;
  private String cuil;
  private String tipoContacto;
  private String valorContacto;
  private String localidad;
  private String latitud;
    private String longitud;
}
