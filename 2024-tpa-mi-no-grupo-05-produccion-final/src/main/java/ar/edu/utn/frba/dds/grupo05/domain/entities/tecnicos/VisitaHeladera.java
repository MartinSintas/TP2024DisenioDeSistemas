package ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos;

import ar.edu.utn.frba.dds.grupo05.converters.LocalDateTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.converters.LocalTimeAttributeConverter;
import ar.edu.utn.frba.dds.grupo05.converters.OptionalByteArrayConverter;
import ar.edu.utn.frba.dds.grupo05.domain.entities.Persistente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.EstadoFallaTecnica;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "visita_heladera")
public class VisitaHeladera extends Persistente {

  @ManyToOne
  @JoinColumn(name = "heladera_id", referencedColumnName = "id")
  private Heladera heladera;

  @ManyToOne
  @JoinColumn(name = "tecnico_id", referencedColumnName = "id")
  private Tecnico tecnico;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado_falla_tecnica", nullable = false)
  private EstadoFallaTecnica estadoFallaTecnica;

  @Column(name = "horario_visita", nullable = false)
  private String horarioDeVisita;

  @Column(name = "foto")
  private String foto;

  @Column(name = "comentario")
  private String comentario;

  @Column(name = "id_incidente")
  private String id_incidente;

}
