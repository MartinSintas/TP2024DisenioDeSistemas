package ar.edu.utn.frba.dds.grupo05.domain.entities.formularios;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeToTimestampConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter //pulir a cuales c/u
@Entity
@Table(name = "respuesta_formulario")
public class RespuestasFormulario extends Persistente {

    @ManyToOne
    @JoinColumn(name = "formulario_id",referencedColumnName = "id")
    private Formulario formulario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "respuesta_formulario_id",referencedColumnName = "id")
    @Column(name = "respuestas")
    List<Respuesta> respuestas;


    public RespuestasFormulario() {
        this.respuestas = new ArrayList<>();
    }

    public void addRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
    }

}
