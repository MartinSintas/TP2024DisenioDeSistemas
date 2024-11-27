package ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "donacion_vianda")
public class DonacionVianda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_humana_id", referencedColumnName = "id")
    private PersonaHumana donante;

    //one to one
    @OneToOne
    @JoinColumn(name = "vianda_id",nullable = false, referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private Vianda vianda;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "fecha_alta", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaAlta;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "fecha_entrega", columnDefinition = "DATE")
    private LocalDateTime fechaEntregada;

}
