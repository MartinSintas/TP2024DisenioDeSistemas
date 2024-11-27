package ar.edu.utn.frba.dds.grupo05.repositories.ubicacion;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class RepositorioLocalidad extends Repositorio {

    public void guardarLocalidad(Localidad localidad) { this.guardar(localidad); }

    public List<Localidad> buscarTodas() {
        return (List<Localidad>) (List<?>) this.buscarTodos("Localidad");
    }

    public void actualizarLocalidad(Localidad localidad) {
        this.actualizar(localidad);
    }

    public void eliminarLocalidad(Localidad localidad) {
        this.eliminar(localidad);
    }

    public Optional<Localidad> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(Localidad.class, id));
    }

    public List<Localidad> buscarPorNombre(String nombre) {
        return entityManager().createQuery("SELECT l FROM Localidad l WHERE l.nombre = :nombre", Localidad.class)
                .setParameter("nombre", nombre)
                .getResultList();
    }
}
