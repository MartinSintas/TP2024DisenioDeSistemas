package ar.edu.utn.frba.dds.grupo05.dtos.inputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.FormaDeColaborar;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargaArchivoCsvInputDTO {
    private TipoDocumento tipoDocumento;
    private String nroDocumento;
    private String nombre;
    private String apellido;
    private String mail;
    private LocalDate fechaColaboracion;
    private FormaDeColaborar formaDeColaborar;
    private Integer cantidad;
    private Integer retirosMinimos;
    private Integer viandasPorMenor;

}
