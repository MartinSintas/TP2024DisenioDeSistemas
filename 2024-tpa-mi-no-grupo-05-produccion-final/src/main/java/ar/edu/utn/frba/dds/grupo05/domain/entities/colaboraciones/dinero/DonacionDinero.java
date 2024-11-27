package ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter //pulir a cuales c/u
@NoArgsConstructor
@Entity
@Table(name = "donacion_dinero")
public class DonacionDinero extends Persistente {

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "fecha_donacion",nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaDonacion;
    @Column(name = "monto",nullable = false)
    private Double monto;

    public DonacionDinero(LocalDateTime fechaDeColaboracion, Double cantidad) {
        this.fechaDonacion = fechaDeColaboracion;
        this.monto = cantidad;
    }
}
