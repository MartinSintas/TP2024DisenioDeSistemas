package ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "distribucion_vianda")
public class DistribucionVianda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "distribucion_id",referencedColumnName = "id")
    @Column(name = "vianda_distribuida")
    private List<Vianda> viandas;

    @ManyToOne
    @JoinColumn(name = "heladera_origen_id", nullable = false, referencedColumnName = "id")
    private Heladera heladeraOrigen;

    @ManyToOne
    @JoinColumn(name = "heladera_destino_id", nullable = false, referencedColumnName = "id")
    private Heladera heladeraDestino;

    @Column(name = "motivo_distribucion", nullable = false)
    private String motivoDistribucion;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "fecha", nullable = false, columnDefinition = "DATE")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "persona_humana_id", nullable = false, referencedColumnName = "id")
    private PersonaHumana personaHumana;

    public DistribucionVianda() {
        this.viandas = new ArrayList<>();
    }

    public void agregarVianda(Vianda vianda){
        this.viandas.add(vianda);
    }

    public Integer cantidadDeViandasAMover(){
        return this.viandas.size();
    }

}
