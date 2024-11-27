package ar.edu.utn.frba.dds.grupo05.dtos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UsuarioInputDTO {
  private String nombreDeUsuario;
  private String contrasenia;
  private TipoRol rol;
}
