package ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DistribucionVianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DonacionVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;

import java.util.List;

public class RepositorioDonacionVianda extends Repositorio {

    public void guardarDonacionVianda(DonacionVianda donacionVianda) {
        this.guardar(donacionVianda);
    }

    public List<DonacionVianda> buscarDonacionVianda() {
        return (List<DonacionVianda>) (List<?>) this.buscarTodos("donacion_vianda");
    }

    public void actualizarDonacionVianda(DonacionVianda donacionVianda) {
        this.actualizar(donacionVianda);
    }

    public void eliminarDonacionVianda(DonacionVianda donacionVianda) {
        this.eliminar(donacionVianda);
    }
}
