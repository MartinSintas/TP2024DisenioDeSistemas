package ar.edu.utn.frba.dds.grupo05.dtos.inputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.FormaDeColaborar;
import ar.edu.utn.frba.dds.grupo05.domain.entities.formularios.RespuestasFormulario;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaHumanaInputDTO {
    private String usuario;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private String nroDocumento;
    private String latitudDireccion;
    private String longitudDireccion;
    private LocalDate fechaNacimiento;
    private MedioDeContactoDTO medioDeContacto;
    private List<FormaDeColaborar> formasDeColaborar = new ArrayList<>();
    private RespuestasFormulario respuestasFormularioDinamico;

    public void addFormaDeColaborar(FormaDeColaborar formaDeColaborar) {
        this.formasDeColaborar.add(formaDeColaborar);
    }
}
