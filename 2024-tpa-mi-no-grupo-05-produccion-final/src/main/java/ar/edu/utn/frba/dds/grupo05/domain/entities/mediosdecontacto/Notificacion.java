package ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "Notificacion")
public class Notificacion extends Persistente {

    @Column(name = "asunto",nullable = false)
    private String asunto;
    @Column(name = "mensaje",nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    public Notificacion(String asunto, String mensaje) {
        this.asunto = asunto;
        this.mensaje = mensaje;
    }
}
