package ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioVianda extends Repositorio {

    public void guardarVianda(Vianda vianda) {
        this.guardar(vianda);
    }


    public List<Vianda> buscarVianda() {

        List<Object> resultado = buscarTodos("Vianda");
        return resultado.stream()
            .filter(Vianda.class::isInstance)
            .map(Vianda.class::cast)
            .collect(Collectors.toList());
    }
    public Optional<Vianda> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(Vianda.class, id));
    }
    public void actualizarVianda(Vianda vianda) {
        this.actualizar(vianda);
    }

    public void eliminarVianda(Vianda vianda) {
        this.eliminar(vianda);
    }
}
