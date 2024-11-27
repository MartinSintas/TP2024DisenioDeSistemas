package ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "retiro_vianda")
public class RetiroVianda extends Persistente {

    @ManyToOne
    @JoinColumn(name = "heladera_id", nullable = false, referencedColumnName = "id")
    private Heladera heladera;

    @ManyToOne
    @JoinColumn(name = "tarjeta_id", nullable = false, referencedColumnName = "id")
    private TarjetaPersonaVulnerable tarjeta;

    @OneToOne
    @JoinColumn(name = "vianda_id", nullable = false, referencedColumnName = "id")
    private Vianda vianda;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "fecha", nullable = false, columnDefinition = "DATE")
    private LocalDateTime fecha;

    public RetiroVianda(Heladera heladera, TarjetaPersonaVulnerable tarjeta) {
        this.heladera = heladera;
        this.tarjeta = tarjeta;
        this.fecha = LocalDateTime.now();
    }

    public String getCodigoTarjetaUsada() {
        return tarjeta.getCodigoTarjeta();
    }
}
