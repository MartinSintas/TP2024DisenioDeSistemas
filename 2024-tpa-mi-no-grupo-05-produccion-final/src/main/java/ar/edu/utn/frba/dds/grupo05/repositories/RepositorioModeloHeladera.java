package ar.edu.utn.frba.dds.grupo05.repositories;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;

import javax.persistence.TypedQuery;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class RepositorioModeloHeladera extends Repositorio {
    public void guardarModeloHeladera(ModeloHeladera modeloHeladera) {
        this.guardar(modeloHeladera);
    }

    public Optional<ModeloHeladera> buscarPorId(Long id) {
        return Optional.ofNullable(this.entityManager().find(ModeloHeladera.class, id));
    }
}
