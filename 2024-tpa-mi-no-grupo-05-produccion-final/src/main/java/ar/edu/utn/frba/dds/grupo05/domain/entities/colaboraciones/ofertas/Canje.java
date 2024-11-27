package ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "canje")
public class Canje extends Persistente {

    @ManyToOne
    @JoinColumn(name = "oferta_id", referencedColumnName = "id")
    private Oferta oferta;

    @Column(name = "puntos_utilizados", nullable = false)
    private Double puntosUtilizados;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "fecha", columnDefinition = "DATETIME")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
