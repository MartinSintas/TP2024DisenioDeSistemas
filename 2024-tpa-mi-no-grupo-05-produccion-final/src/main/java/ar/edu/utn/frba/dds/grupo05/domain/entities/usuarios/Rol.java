package ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol extends Persistente {
  private String nombre;
  private TipoDeRol tipo;

  @ManyToMany
  @JoinTable(name = "rol_permisos",
             joinColumns = @JoinColumn(name = "rol_id"),
             inverseJoinColumns = @JoinColumn(name = "permiso_id"))
  private Set<Permiso> permisos;


  public Rol() {
    this.permisos = new HashSet<>();
  }

  public void agregarPermisos(Permiso... permisosNuevos) {
    Collections.addAll(this.permisos, permisosNuevos);
  }

  public boolean comprobarPermiso(Permiso permiso) {
    return this.permisos.contains(permiso);
  }

  public boolean comprobarPermiso(String nombrePermitido) {
    return this.permisos.stream().anyMatch(p -> p.coincidePorNombre(nombrePermitido));
  }
}

