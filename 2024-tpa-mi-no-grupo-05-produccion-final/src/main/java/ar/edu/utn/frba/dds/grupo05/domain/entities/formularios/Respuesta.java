package ar.edu.utn.frba.dds.grupo05.domain.entities.formularios;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter //pulir a cuales c/u
@Entity
@Table(name = "respuesta")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta extends Persistente {

    @Column(name = "desarrollo")
    private String desarrollo;

    @ManyToOne
    @JoinColumn(name = "enunciado_id", referencedColumnName = "id")
    private Enunciado enunciado;
}


