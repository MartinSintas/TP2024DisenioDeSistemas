package ar.edu.utn.frba.dds.grupo05.services.verificadorconexionheladeras;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.Alerta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.TipoAlerta;
import ar.edu.utn.frba.dds.grupo05.repositories.IAlertaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;

import java.time.LocalDateTime;
import java.util.List;

public class VerificadorConexionHeladeras {
    private IHeladeraRepository heladeraRepository;
    private IAlertaRepository alertaRepository;

    public VerificadorConexionHeladeras(IHeladeraRepository heladeraRepository, IAlertaRepository alertaRepository) {
        this.heladeraRepository = heladeraRepository;
        this.alertaRepository = alertaRepository;
    }

    private List<Heladera> obtenerHeladerasSinConexionHace(Integer minutos) {
        List<Heladera> heladeras = heladeraRepository.buscarTodas();
        List<Heladera> heladerasSinConexion = heladeras.stream()
                .filter(heladera -> heladera
                                    .getFechaUltimaConexion()
                                    .isBefore(LocalDateTime.now().minusMinutes(minutos)))
                .toList();
        return heladerasSinConexion;
    }

    public void generarAlertaHeladerasSinConexion(Integer minutos) {
        List<Heladera> heladerasSinConexion = obtenerHeladerasSinConexionHace(minutos);
        for (Heladera heladera : heladerasSinConexion) {
            heladera.ponerEnAlertaConexion();
            heladeraRepository.guardar(heladera);
            Alerta alerta = new Alerta(LocalDateTime.now(), heladera, TipoAlerta.DESCONEXION);
            alertaRepository.guardar(alerta);
        }
    }
}
