package ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeToTimestampConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "registro_temperatura")
public class RegistroTemperatura extends Persistente {


    @Column(name = "temperatura", nullable = false)
    private Double temperatura;

    @Convert(converter = LocalDateTimeToTimestampConverter.class)
    @Column(name = "fecha", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime fecha;
}
