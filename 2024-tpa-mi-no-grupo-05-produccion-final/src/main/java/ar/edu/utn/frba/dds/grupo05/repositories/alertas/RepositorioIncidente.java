package ar.edu.utn.frba.dds.grupo05.repositories.alertas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.Incidente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.VisitaHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioIncidente extends Repositorio {

    public List<Incidente> buscarIncidente() {
        List<Object> resultado = buscarTodos("Incidente");
        return resultado.stream()
                .filter(Incidente.class::isInstance)
                .map(Incidente.class::cast)
                .collect(Collectors.toList());
    }

    public Optional<Incidente> buscarPorId(Long id) {
        return Optional.ofNullable(this.entityManager().find(Incidente.class, id));
    }
}
