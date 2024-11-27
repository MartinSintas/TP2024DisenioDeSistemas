package ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.ofertas;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Oferta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;

public class RepositorioOferta extends Repositorio {

    public void guardarOferta(Oferta oferta) {
        this.guardar(oferta);
    }

    public List<Oferta> buscarOfertas() {
        List<Oferta> ofertas = this.buscarTodosPorClase(Oferta.class);

        if (ofertas.isEmpty()) {
            return null;
        }
        return ofertas;
    }
    public Optional<Oferta> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager().find(Oferta.class, id));
    }

    public void actualizarOferta(Oferta oferta) {
        this.actualizar(oferta);
    }

    public void eliminarOferta(Oferta oferta) {
        this.eliminar(oferta);
    }
}
