package ar.edu.utn.frba.dds.grupo05.repositories;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class Repositorio implements WithSimplePersistenceUnit {

  public void guardar(Object o){
    EntityTransaction tx = entityManager().getTransaction();
    if(!tx.isActive())
      tx.begin();
    entityManager().persist(o);
    tx.commit();
  }

  @SuppressWarnings("unchecked")
  public List<Object>   buscarTodos(String nombreDeClase) {
    // Asegúrate de añadir un espacio entre 'from' y el nombre de la clase
    String query = "from " + nombreDeClase;
    return entityManager().createQuery(query).getResultList();
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> buscarTodosPorClase(Class<T> clase) {
    String query = "from " + clase.getName();
    return entityManager().createQuery(query, clase).getResultList();
  }


  public void actualizar(Object o){
    EntityTransaction tx = entityManager().getTransaction();
    if(!tx.isActive())
      tx.begin();
    entityManager().merge(o);
    tx.commit();
  }

  public void eliminar(Object o){
    EntityTransaction tx = entityManager().getTransaction();
    if(!tx.isActive())
      tx.begin();
    entityManager().remove(o);
    tx.commit();
  }

  public Optional<Object> buscarPorId(Class<?> clase, Long id) {
    return Optional.ofNullable(entityManager().find(clase, id));
  }
}
