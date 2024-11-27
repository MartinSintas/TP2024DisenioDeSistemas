package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.FallaTecnica;

import java.util.List;

public interface IFallaTecnicaRepository {
    public void guardar(FallaTecnica fallaTecnica);
    public List<FallaTecnica> getAll();
}
