package ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.colaboradores;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.AperturaHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;

public class RepositorioAperturaHeladera extends Repositorio {

    public void guardarAperturaHeladera(AperturaHeladera aperturaHeladera) {
        this.guardar(aperturaHeladera);
    }

    public List<AperturaHeladera> buscarAperturaHeladera() {
        return (List<AperturaHeladera>) (List<?>) this.buscarTodos("apertura_heladera");
    }

    public Optional<AperturaHeladera> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(AperturaHeladera.class, id));
    }

    public void actualizarAperturaHeladera(AperturaHeladera aperturaHeladera) {
        this.actualizar(aperturaHeladera);
    }

    public void eliminarAperturaHeladera(AperturaHeladera aperturaHeladera) {
        this.eliminar(aperturaHeladera);
    }
}
