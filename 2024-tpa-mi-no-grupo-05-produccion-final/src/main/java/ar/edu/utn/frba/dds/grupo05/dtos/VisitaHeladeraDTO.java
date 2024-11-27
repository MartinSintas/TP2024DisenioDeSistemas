package ar.edu.utn.frba.dds.grupo05.dtos;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class VisitaHeladeraDTO {

    private String id_incidente;
    private String fecha;
    private String comentario;
    private String path;
    private String resuelto;
    private Heladera heladera;
    private Tecnico tecnico;
}
