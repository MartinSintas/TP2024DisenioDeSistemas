package ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.solicitudesApertura;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeToTimestampConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitud_apertura_heladera")
public class SolicitudAperturaHeladera extends Persistente {
    @ManyToOne //muchas personas pueden abrir la heladera
    @JoinColumn(name = "persona_humana_id", referencedColumnName = "id")
    private PersonaHumana actor;
    @ManyToOne
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private Heladera heladera;
    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    @Column(name = "fecha_registro_solicitud", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaRegistroSolicitud;

    public SolicitudAperturaHeladera() {
        this.fechaRegistroSolicitud = LocalDateTime.now();
    }



    //Esta seria la interfaz saliente? Osea en realidad a la heladera hay que mandarle dircto el string de uuid de la tarjeta
}
