package ar.edu.utn.frba.dds.grupo05.dtos.inputs;

import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.TipoContacto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MedioDeContactoDTO {
  TipoContacto tipo;
  String valor;
}
