package ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.solicitudesApertura;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DonacionVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.Repositorio;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.solicitudesApertura.SolicitudAperturaHeladera;

import java.util.List;

public class RepositorioSolicitudAperturaHeladera extends Repositorio {

    public void guardarSolicitudAperturaHeladera(SolicitudAperturaHeladera solicitudAperturaHeladera) {
        this.guardar(solicitudAperturaHeladera);
    }

    public List<SolicitudAperturaHeladera> buscarSolicitudAperturaHeladera() {
        return (List<SolicitudAperturaHeladera>) (List<?>) this.buscarTodos("solicitud_apertura_heladera");
    }

    public void actualizarSolicitudAperturaHeladera(SolicitudAperturaHeladera solicitudAperturaHeladera) {
        this.actualizar(solicitudAperturaHeladera);
    }

    public void eliminarSolicitudAperturaHeladera(SolicitudAperturaHeladera solicitudAperturaHeladera) {
        this.eliminar(solicitudAperturaHeladera);
    }
}
