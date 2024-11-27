package ar.edu.utn.frba.dds.grupo05.domain.entities.personas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rubro")
public class Rubro extends Persistente {
    @Setter
    @Getter
    @Column(name = "nombre",nullable = false)
    private String nombre;
}
