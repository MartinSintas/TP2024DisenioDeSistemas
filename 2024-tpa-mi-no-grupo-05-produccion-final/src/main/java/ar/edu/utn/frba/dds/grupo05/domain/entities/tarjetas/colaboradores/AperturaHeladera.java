package ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeToTimestampConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "apertura_heladera")
public class AperturaHeladera extends Persistente {

    @Enumerated(EnumType.STRING)
    @Column(name = "motivo_apertura", nullable = false)
    private MotivoApertura motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_apertura", nullable = false)
    private EstadoApertura estado;

    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    @Column(name = "hora_solicitud", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime horaSolicitud;
    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    @Column(name = "hora_limite", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime horaLimite;
    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    @Column(name = "hora_apertura", columnDefinition = "TIMESTAMP")
    private LocalDateTime horaApertura;

    @ManyToOne
    @JoinColumn(name = "heladera_id", nullable = false, referencedColumnName = "id")
    private Heladera heladera;

    @OneToOne
    @JoinColumn(name = "tarjeta_id", nullable = false, referencedColumnName = "id")
    private TarjetaColaborador tarjeta;

    public void registrarAperturaExitosa(LocalDateTime horaApertura) {
        this.horaApertura = horaApertura;
        this.estado = EstadoApertura.REALIZADA;
    }

    public void registrarAperturaFallida() {
        this.estado = EstadoApertura.FALLIDA;
    }
}
