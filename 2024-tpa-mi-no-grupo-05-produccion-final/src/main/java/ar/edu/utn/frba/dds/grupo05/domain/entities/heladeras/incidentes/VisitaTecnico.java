package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visita_tecnico")
public class VisitaTecnico extends Persistente {

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "fecha_visita", nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaVisita;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "incidente_id", nullable = false, referencedColumnName = "id")
    private Incidente incidente;

    @Setter
    private String foto;

    public VisitaTecnico(LocalDateTime fechaVisita, String descripcion, Incidente incidente) {
        this.fechaVisita = fechaVisita;
        this.descripcion = descripcion;
        this.incidente = incidente;
    }
}
