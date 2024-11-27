package ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.colaboradores;

import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.TarjetaColaborador;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.personasvulnerables.EstadoTarjeta;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;
import java.util.Optional;

public class RepositorioTarjetaColaborador extends Repositorio {

    public void guardarTarjetaColaborador(TarjetaColaborador tarjetaColaborador) {
        this.guardar(tarjetaColaborador);
    }

    public List<TarjetaColaborador> buscarTodosLosTecnicos() {
        return (List<TarjetaColaborador>) (List<?>) this.buscarTodos("tarjeta_colaborador");
    }

    public void actualizarTarjetaColaborador(TarjetaColaborador tarjetaColaborador) {
        this.actualizar(tarjetaColaborador);
    }

    public List<TarjetaColaborador> buscarPorColaborador(PersonaHumana colaborador) {

        return (List<TarjetaColaborador>) (List<?>) this.entityManager()
                .createQuery("from TarjetaColaborador where owner = :colaborador and estadoTarjeta = :estadoTarjeta")
                .setParameter("colaborador", colaborador)
                .setParameter("estadoTarjeta", EstadoTarjeta.ACTIVA)
                .getResultList();

    }

    public void eliminarTarjetaColaborador(TarjetaColaborador tarjetaColaborador) {
        this.eliminar(tarjetaColaborador);
    }
}
