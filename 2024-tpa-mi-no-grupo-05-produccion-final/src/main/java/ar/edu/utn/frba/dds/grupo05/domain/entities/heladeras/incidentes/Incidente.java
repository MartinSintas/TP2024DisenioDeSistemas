package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity @Table(name = "incidente")
public class Incidente extends Persistente {
    @Setter
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "fecha_incidente", nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaIncidente;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "heladera_id", referencedColumnName = "id")
    private Heladera heladera;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_incidente")
    private TipoIncidente tipoIncidente;

    @Setter
    @Column(name = "foto_resolucion", nullable = true)
    private String fotoResolucion;

    @OneToMany(mappedBy = "incidente", cascade = CascadeType.ALL)
    private List<VisitaTecnico> visitas;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_incidente", nullable = false)
    private EstadoIncidente estadoIncidente;

    @Setter
    @ManyToOne
    @JoinColumn(name = "tecnico_asignado_id", referencedColumnName = "id")
    private Tecnico tecnicoAsignado;

    public Incidente() {
        this.visitas = new ArrayList<>();
    }
}
