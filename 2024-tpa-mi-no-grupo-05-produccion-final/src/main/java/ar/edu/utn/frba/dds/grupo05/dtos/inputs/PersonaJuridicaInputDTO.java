package ar.edu.utn.frba.dds.grupo05.dtos.inputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.MedioDeContacto;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.Rubro;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.TipoJuridico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaJuridicaInputDTO {
  private String razonSocial;
  private TipoJuridico tipo;
  private Rubro rubro;
  private List<MedioDeContacto> mediosDeContacto;
}
