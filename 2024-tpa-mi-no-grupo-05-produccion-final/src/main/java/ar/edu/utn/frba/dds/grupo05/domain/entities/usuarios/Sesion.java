package ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Sesion")
public class Sesion {
  @Id
  @GeneratedValue
  private UUID cookie;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  @Column(name = "rol")
  private TipoRol rol;
}
