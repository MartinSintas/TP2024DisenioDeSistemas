package ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DistribucionVianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;

public class RepositorioDistribucionVianda extends Repositorio {


    public void guardarDistribucionVianda(DistribucionVianda distribucionVianda) {
        this.guardar(distribucionVianda);
    }

    public List<DistribucionVianda> buscarDistribucionVianda() {
        return (List<DistribucionVianda>) (List<?>) this.buscarTodos("distribucion_vianda");
    }

    public void actualizarDistribucionVianda(DistribucionVianda distribucionVianda) {
        this.actualizar(distribucionVianda);
    }
    public Optional<DistribucionVianda> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(DistribucionVianda.class, id));
    }

    public void eliminarDistribucionVianda(DistribucionVianda distribucionVianda) {
        this.eliminar(distribucionVianda);
    }
}
