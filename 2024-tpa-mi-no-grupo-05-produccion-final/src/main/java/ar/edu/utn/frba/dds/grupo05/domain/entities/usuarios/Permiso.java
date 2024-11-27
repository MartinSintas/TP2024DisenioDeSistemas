package ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "permiso")
@Entity
public class Permiso extends Persistente {
  private String nombre;
  public boolean coincidePorNombre(String unNombre) {
    return this.nombre.equals(unNombre);
  }
}
