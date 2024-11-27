package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeToTimestampConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movimiento_heladera")
public class MovimientoHeladera extends Persistente {


    @OneToMany
    @JoinColumn(name = "movimiento_heladera_id",referencedColumnName = "id")
    @Column(name = "vianda")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Vianda> viandas;

    @ManyToOne(optional = false)
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private Heladera heladera;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    @Column(name = "fecha_movimiento",columnDefinition = "TIMESTAMP")
    private LocalDateTime fecha;

    public MovimientoHeladera() {
        this.viandas = new ArrayList<>();
    }

    public Integer cantidadDeViandas(){
        return this.viandas.size();
    }
}
