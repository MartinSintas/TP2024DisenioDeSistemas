package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "modelo_heladera")
public class ModeloHeladera extends Persistente {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "detalle", nullable = false)
    private String detalle;

    @Column(name = "temperatura_maxima", nullable = false)
    private Double temperaturaMaxima;

    @Column(name = "temperatura_minima", nullable = false)
    private Double temperaturaMinima;

    public ModeloHeladera() {
    }

    public Boolean temperaturaEnRango(Double temperatura){
        return temperatura > temperaturaMinima && temperatura < temperaturaMaxima;
    }

}
