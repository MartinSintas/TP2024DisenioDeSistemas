package ar.edu.utn.frba.dds.grupo05.dtos.outputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaHumanaOutputDTO {
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private String nroDocumento;
    private String domicilio;
    private LocalDate fechaNacimiento;
    private MedioDeContacto medioDeContacto;
    private Long id;
}
