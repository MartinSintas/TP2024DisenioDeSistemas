package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.Notificacion;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "alerta")
public class Alerta extends Persistente {

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "fecha_reporte_incidente", nullable = false, columnDefinition = "DATE")
    private LocalDateTime fechaReporteDelIncidente;

    @ManyToOne
    @JoinColumn(name = "heladera_id", nullable = false, referencedColumnName = "id")
    private Heladera heladera;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alerta")
    private TipoAlerta tipoAlerta;

    @OneToOne
    @JoinColumn(name = "incidente_id", nullable = false, referencedColumnName = "id")
    private Incidente incidente;

    public Alerta(LocalDateTime fechaReporte, Heladera heladera,
                  TipoAlerta tipoAlerta) {
        this.fechaReporteDelIncidente = fechaReporte;
        this.heladera = heladera;
        this.tipoAlerta = tipoAlerta;
    }

//    public void dispararAlarma() {
//        heladera.ponerEnInactiva();
//        List<Heladera> heladerasEnLocalidad = heladera.heladerasEnLocalidad();
//
//        String nombreHeladera = heladera.getNombreSignificativo();
//
//
//        String ubicacion = heladera.getUbicacion().getCalle() +
//            " " +
//            heladera.getUbicacion().getAltura() +
//            ", " +
//            heladera.getUbicacion().getLocalidad().getNombre();
//        String mensaje = "La heladera " + nombreHeladera +
//            " ubicada en " + ubicacion +
//            " sufrio un desperfecto de tipo " + getTipoAlerta() +
//            "Espacio disponible en heladeras de la misma localidad:\n";
//
//
//        for (Heladera h : heladerasEnLocalidad) {
//            if (!h.equals(heladera) && h.puedeRecibirViandas(1)) {
//                mensaje += h.getNombreSignificativo() +
//                    ": " + h.espacioDisponible() +
//                    " espacios disponibles.\n";
//            }
//            Notificacion notificacion = new Notificacion(
//                mensaje,
//                "Incidente en la heladera"
//            );
//            heladera.notificarSubscriptores(notificacion);
//        }
//   }
}