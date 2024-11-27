package ar.edu.utn.frba.dds.grupo05.dtos.inputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaVulnerable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaVulnerableInputDTO {
    private String nombre;
    private LocalDateTime fechaNacimiento;
    private boolean situacionCalle;
    private String domicilio;
    private String latitud;
    private String longitud;
    private String tipoDocumento;
    private String nroDocumento;
    private String numeroTarjeta;
    private List<PersonaVulnerable> menoresACargo;


}
