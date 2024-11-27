package ar.edu.utn.frba.dds.grupo05.repositories.heladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones.SuscripcionHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioSuscripcionHeladera extends Repositorio {
  public void guardarSuscripcion(SuscripcionHeladera suscripcion) {
    this.guardar(suscripcion);
  }

  public List<SuscripcionHeladera> buscarSuscripcion() {

    List<Object> resultado = buscarTodos(SuscripcionHeladera.class.getName());
    // Convierte el resultado de Object a List<Heladera>
    return resultado.stream()
            .filter(SuscripcionHeladera.class::isInstance)
            .map(SuscripcionHeladera.class::cast)
            .collect(Collectors.toList());
  }

  public void actualizarSuscripcion(SuscripcionHeladera suscripcion) {
    this.actualizar(suscripcion);
  }

  public void eliminarSuscripcion(SuscripcionHeladera suscripcion) {
    this.eliminar(suscripcion);
  }

  public Optional<SuscripcionHeladera> buscarPorId(Long id) {
    return Optional.ofNullable(entityManager().find(SuscripcionHeladera.class, id));
  }
}
