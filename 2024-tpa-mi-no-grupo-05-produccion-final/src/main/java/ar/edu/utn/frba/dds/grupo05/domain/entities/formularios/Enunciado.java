package ar.edu.utn.frba.dds.grupo05.domain.entities.formularios;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter //pulir a cuales c/u
@Entity
@Table(name = "enunciado")
public class Enunciado extends Persistente {

    @Column(name = "enunciado", unique = true)
    private String enunciado;

    @Column(name = "name")
    private String name;

}
