package ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@Table(name = "vianda")

@Getter
@Setter
public class Vianda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comida_detalle", nullable = false)
    private String comidaDetalle;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "fecha_vencimiento",nullable = false, columnDefinition = "DATE")
    private LocalDate fechaCaducidad;

    @Column(name = "peso_vianda",nullable = false)
    private Integer pesoVianda;

    @Column(name = "calorias")
    private Integer calorias;

    @Column(name = "entregada")
    private Boolean fueEntregada = false;


    public Vianda(){
        this.fueEntregada = false;
    }
}


