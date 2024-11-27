package ar.edu.utn.frba.dds.grupo05.services;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.VisitaHeladera;
import ar.edu.utn.frba.dds.grupo05.dtos.VisitaHeladeraDTO;
import ar.edu.utn.frba.dds.grupo05.factories.ModeloHeladeraFactory;
import ar.edu.utn.frba.dds.grupo05.factories.VisitaHeladeraFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.tecnico.RepositorioVisitaHeladera;

import java.util.List;

public class VisitaHeladeraService {

    private RepositorioVisitaHeladera repositorioVisitaHeladera;

    public VisitaHeladeraService(RepositorioVisitaHeladera repositorioVisitaHeladeras){
        this.repositorioVisitaHeladera = repositorioVisitaHeladeras;
    }
    public void create(VisitaHeladeraDTO visitaHeladeraDTO){
        try {
            VisitaHeladera visitaHeladera = VisitaHeladeraFactory.fromDTO(visitaHeladeraDTO);
            this.repositorioVisitaHeladera.guardarVisitaHeladera(visitaHeladera);
        } catch (Exception e) {
            System.err.println("Error al crear la visita heladera: " + e.getMessage());
            throw new RuntimeException("Error al crear visita: " + visitaHeladeraDTO.getId_incidente(), e);
        }
    }
    public List<Object> obtenerVisitas() {
        return this.repositorioVisitaHeladera.buscarTodos("VisitaHeladera");
    }

}
