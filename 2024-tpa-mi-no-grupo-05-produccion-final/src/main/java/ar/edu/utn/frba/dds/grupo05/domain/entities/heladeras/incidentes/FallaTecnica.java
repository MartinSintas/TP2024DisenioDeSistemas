package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "falla_tecnica")
public class FallaTecnica extends Persistente {


    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "fecha_reporte_incidente", nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaReporteDelIncidente;

    @ManyToOne
    @JoinColumn(name = "heladera_id", nullable = false, referencedColumnName = "id")
    private Heladera heladera;

    @OneToOne
    @JoinColumn(name = "persona_humana_id", nullable = false, referencedColumnName = "id")
    private PersonaHumana colaborador;

    @Column(name = "descripcion",nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "foto", nullable = false)
    private String foto;

    @OneToOne
    @JoinColumn(name = "tecnico_id", nullable = false, referencedColumnName = "id")
    private Tecnico tecnicoAsignado;

    @OneToOne
    @JoinColumn(name = "incidente_id", nullable = false, referencedColumnName = "id")
    private Incidente incidente;


    public FallaTecnica(LocalDateTime fechaReporte, String descripcion, String foto) {
        this.fechaReporteDelIncidente = fechaReporte;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    /*public void agregarVisita(VisitaTecnico visita) {
        this.visitas.add(visita);
    }*/

}
