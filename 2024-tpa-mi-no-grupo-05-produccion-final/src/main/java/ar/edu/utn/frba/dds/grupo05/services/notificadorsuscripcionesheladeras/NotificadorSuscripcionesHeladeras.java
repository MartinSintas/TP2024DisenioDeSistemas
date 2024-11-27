package ar.edu.utn.frba.dds.grupo05.services.notificadorsuscripcionesheladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.suscripciones.SuscripcionHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.ISuscripcionHeladeraRepository;

import java.util.List;

public class NotificadorSuscripcionesHeladeras {
    private final ISuscripcionHeladeraRepository suscripcionHeladeraRepository;

    // Inyección de dependencias
    public NotificadorSuscripcionesHeladeras(ISuscripcionHeladeraRepository suscripcionHeladeraRepository) {
        this.suscripcionHeladeraRepository = suscripcionHeladeraRepository;
    }

    public void notificarSuscripciones() {
        obtenerSuscripcionesQueCumplenCondiciones().forEach(SuscripcionHeladera::notificar);
    }

    private List<SuscripcionHeladera> obtenerSuscripcionesQueCumplenCondiciones() {
        return suscripcionHeladeraRepository
                .findAll()
                .stream()
                .filter(SuscripcionHeladera::cumpleCondicion)
                .toList();
    }

}
