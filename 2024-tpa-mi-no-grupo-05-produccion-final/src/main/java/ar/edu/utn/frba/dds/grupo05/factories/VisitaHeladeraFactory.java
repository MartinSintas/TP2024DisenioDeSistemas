package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.EstadoFallaTecnica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.VisitaHeladera;
import ar.edu.utn.frba.dds.grupo05.dtos.VisitaHeladeraDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.ThreadLocalRandom;

public class VisitaHeladeraFactory {

    public static VisitaHeladera fromDTO(VisitaHeladeraDTO visitaHeladeraDTO) {
        VisitaHeladera visitaHeladera = new VisitaHeladera();

        visitaHeladera.setId_incidente(visitaHeladeraDTO.getId_incidente());
        visitaHeladera.setComentario(visitaHeladeraDTO.getComentario());
        visitaHeladera.setHorarioDeVisita(visitaHeladeraDTO.getFecha());
        visitaHeladera.setFoto(visitaHeladeraDTO.getPath());
        visitaHeladera.setEstadoFallaTecnica(EstadoFallaTecnica.valueOf(visitaHeladeraDTO.getResuelto()));
        visitaHeladera.setHeladera(visitaHeladeraDTO.getHeladera());
        visitaHeladera.setTecnico(visitaHeladeraDTO.getTecnico());
        return visitaHeladera;
    }

}
