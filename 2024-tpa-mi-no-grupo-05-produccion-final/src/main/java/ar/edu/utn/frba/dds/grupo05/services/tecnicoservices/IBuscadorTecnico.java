package ar.edu.utn.frba.dds.grupo05.services.tecnicoservices;

import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;

public interface IBuscadorTecnico {
    public Tecnico buscarTecnicoMasCercano(PuntoGeografico puntoGeografico);


}
