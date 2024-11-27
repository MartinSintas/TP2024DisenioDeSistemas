package ar.edu.utn.frba.dds.grupo05.domain.entities.zona;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Vector;
@Getter
@Setter
@Entity
@Table(name = "zona")
public class Zona extends Persistente {
    @Column(name = "nombre", nullable = false)
    private String nombreZona; // va a ser el nombre de la localidad para referencia

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
    private PuntoGeografico centroZona;

    @Column(name = "radio_maximo",nullable = false)
    private Integer radioMaximoZona;

    public Integer getRadioMaximoZona() {
        return radioMaximoZona;
    }

    public PuntoGeografico getCentroZona() {
        return centroZona;
    }
    public String getNombreZona() {
        return nombreZona;
    }

}
