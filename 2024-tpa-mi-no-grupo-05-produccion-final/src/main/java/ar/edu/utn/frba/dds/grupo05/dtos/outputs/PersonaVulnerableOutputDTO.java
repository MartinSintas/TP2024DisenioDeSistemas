package ar.edu.utn.frba.dds.grupo05.dtos.outputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaVulnerableOutputDTO {
    private Long id;
    private String nombre;
    private LocalDateTime fechaNacimiento;
    private LocalDateTime fechaRegistro;
    private boolean situacionCalle;
    private String domicilio;
    private String tipoDocumento;
    private String nroDocumento;
    private String numeroTarjeta;
    private List<PersonaVulnerableOutputDTO> menoresACargo;
}
