package ar.edu.utn.frba.dds.grupo05.services.fallatecnica;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.Incidente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.TipoIncidente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.Notificacion;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.VisitaHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.factories.HeladeraFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.alertas.RepositorioIncidente;
import ar.edu.utn.frba.dds.grupo05.services.tecnicoservices.IBuscadorTecnico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class IncidenteService {

    private RepositorioIncidente repositorioIncidente;
    private IBuscadorTecnico buscadorTecnico;

    public IncidenteService(RepositorioIncidente repositorioIncidente, IBuscadorTecnico buscadorTecnico) {
        this.repositorioIncidente = repositorioIncidente;
        this.buscadorTecnico = buscadorTecnico;
    }

    public Incidente crearIncidente(Heladera heladera, TipoIncidente tipoIncidente){
        Incidente incidente = new Incidente();
        incidente.setHeladera(heladera);
        incidente.setTipoIncidente(tipoIncidente);
        incidente.setFechaIncidente(LocalDateTime.now());
        incidente.setEstadoIncidente(EstadoIncidente.SIN_ASIGNAR);

        PuntoGeografico puntoGeografico = new PuntoGeografico();
        puntoGeografico.setLocalidad(heladera.getLocalidad());
        puntoGeografico.setLongitud(heladera.getLongitud());
        puntoGeografico.setLatitud(heladera.getLatitud());

        Tecnico tecnicoMasCercano = buscadorTecnico.buscarTecnicoMasCercano(puntoGeografico);
        if(tecnicoMasCercano != null){
            incidente.setTecnicoAsignado(tecnicoMasCercano);
            incidente.setEstadoIncidente(EstadoIncidente.ASIGNADO_PENDIENTE);
            this.notificarTecnico(tecnicoMasCercano, heladera);
        }

        repositorioIncidente.guardar(incidente);

        return incidente;
    }

    public List<IncidenteDTO> obtenerTodos(){
        List<Incidente> incidentes = repositorioIncidente.buscarIncidente();
        return IncidenteFactory.fromEntity(incidentes);
    }

    private void notificarTecnico(Tecnico tecnico, Heladera heladera) {
        String asunto = "La heladera " + heladera.getNombreSignificativo() + " tiene un incidente.";
        String mensaje = "La heladera " + heladera.getNombreSignificativo() + ", ubicada en " +
                heladera.getDireccion() + " " +
                heladera.getLocalidad().getNombre()
                + " tiene un incidente. La heladera está descompuesta, su temperatura está fuera del rango. " +
                "Por favor, acuda a la heladera lo antes posible.";
        Notificacion notificacion = new Notificacion(asunto, mensaje);

        tecnico.notificar(notificacion);
    }

    public Optional<Incidente> buscarPorId(Long id) {
        return this.repositorioIncidente.buscarPorId(id);
    }

}
