package ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero;

import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter //pulir a cuales c/u
@Entity
@Table(name = "donacion_dinero_programada")
public class DonacionDineroProgramada extends Persistente {

    @OneToOne
    @JoinColumn(name = "donacion_dinero_id",referencedColumnName = "id")
    private DonacionDinero ultimaDonacion;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "dias_para_repetir")
    private Integer diasParaRepetir;

    public Boolean yaSePuedeRepetir(){
        return this.ultimaDonacion
                .getFechaDonacion()
                .plusDays(diasParaRepetir)
                .isAfter(LocalDateTime.now());
    }
}
