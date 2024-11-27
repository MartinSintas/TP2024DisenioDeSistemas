package ar.edu.utn.frba.dds.grupo05.repositories.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioFallaTecnica extends Repositorio {

    public void guardarFallaTecnica(FallaTecnica fallaTecnica) {
        this.guardar(fallaTecnica);
    }

    public List<FallaTecnica> buscarFallaTecnica() {
        return (List<FallaTecnica>) (List<?>) this.buscarTodos("falla_tecnica");
    }

    public void actualizarFallaTecnica(FallaTecnica fallaTecnica) {
        this.actualizar(fallaTecnica);
    }

    public void eliminarFallaTecnica(FallaTecnica fallaTecnica) {
        this.eliminar(fallaTecnica);
    }
}
