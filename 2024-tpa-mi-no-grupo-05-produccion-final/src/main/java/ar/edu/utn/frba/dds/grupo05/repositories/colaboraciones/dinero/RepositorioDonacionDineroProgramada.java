package ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.dinero;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.dinero.DonacionDineroProgramada;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Oferta;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioDonacionDineroProgramada extends Repositorio{

    public void guardarDonacionDineroProgramada(DonacionDineroProgramada donacionDineroProgramada) {
        this.guardar(donacionDineroProgramada);
    }

    public List<DonacionDineroProgramada> buscarDonacionDineroProgramada() {
        return (List<DonacionDineroProgramada>) (List<?>) this.buscarTodos("donacion_dinero_programada");
    }

    public void actualizarDonacionDineroProgramada(DonacionDineroProgramada donacionDineroProgramada) {
        this.actualizar(donacionDineroProgramada);
    }

    public void eliminarDonacionDineroProgramada(DonacionDineroProgramada donacionDineroProgramada) {
        this.eliminar(donacionDineroProgramada);
    }
}
