package ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.dinero;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero.DonacionDineroProgramada;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero.DonacionDinero;

import java.util.List;

public class RepositorioDonacionDinero extends Repositorio {

    public void guardarDonacionDinero(DonacionDinero donacionDinero) {
        this.guardar(donacionDinero);
    }

    public List<DonacionDinero> buscarDonacionDinero() {
        return (List<DonacionDinero>) (List<?>) this.buscarTodos("donacion_dinero");
    }

    public void actualizarDonacionDinero(DonacionDinero donacionDinero) {
        this.actualizar(donacionDinero);
    }

    public void eliminarDonacionDinero(DonacionDinero donacionDinero) {
        this.eliminar(donacionDinero);
    }
}
