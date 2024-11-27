package ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario extends Persistente {
  @Column(name = "nombre_de_usuario", nullable = false, unique = true)
  private String nombreDeUsuario;

  @Column(name = "contrasenia", nullable = false)
  private String contrasenia;

  @Enumerated(EnumType.STRING)
  @Column(name = "rol")
  private TipoRol rol;
}
