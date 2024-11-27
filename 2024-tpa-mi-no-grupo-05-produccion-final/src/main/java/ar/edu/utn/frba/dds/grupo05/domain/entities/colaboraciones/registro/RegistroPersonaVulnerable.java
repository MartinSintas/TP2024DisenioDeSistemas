package ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.registro;


import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaVulnerable;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.TarjetaPersonaVulnerable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "registro_persona_vulnerable")
public class RegistroPersonaVulnerable extends Persistente {

    //una persona es registrada una vez
    @OneToOne(optional = false)
    @JoinColumn(name = "persona_vulnerable_id",nullable = false)
    private PersonaVulnerable personaVulnerable;

    //esta tarjeta entregada va a ser única, sin embargo la persona vulnerable puede tener varias tarjetas(1 activa)
    @OneToOne(optional = false)
    @JoinColumn(name = "tarjeta_entregada_id", nullable = false)
    private TarjetaPersonaVulnerable tarjetaEntregada;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "fecha",columnDefinition = "DATE", nullable = false)
    private LocalDateTime fecha;

}
