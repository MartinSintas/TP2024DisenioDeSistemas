package ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "punto_geografico")
public class PuntoGeografico extends Persistente {

    @Column(name = "latitud", nullable = false)
    private String latitud;

    @Column(name = "longitud", nullable = false)
    private String longitud;

    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private String altura;

    @ManyToOne
    @JoinColumn(name = "localidad_id", referencedColumnName = "id")
    private Localidad localidad;

    public PuntoGeografico(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
