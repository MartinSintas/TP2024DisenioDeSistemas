package ar.edu.utn.frba.dds.grupo05.services.fallatecnica.impl;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.FallaTecnica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.mediosdecontacto.Notificacion;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.FallaTecnicaInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.FallaTecnicaOutputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.HeladeraNotFoundException;
import ar.edu.utn.frba.dds.grupo05.exceptions.PersonaHumanaNotFoundException;
import ar.edu.utn.frba.dds.grupo05.factories.FallaTecnicaFactory;
import ar.edu.utn.frba.dds.grupo05.repositories.IFallaTecnicaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.IPersonaHumanaRepository;
import ar.edu.utn.frba.dds.grupo05.services.fallatecnica.IFallaTecnicaService;
import ar.edu.utn.frba.dds.grupo05.services.tecnicoservices.IBuscadorTecnico;
import ar.edu.utn.frba.dds.grupo05.utils.calcularDistanciaEntrePuntos.CalculoDistanciaEntrePuntos;

import java.util.Optional;

public class FallaTecnicaService implements IFallaTecnicaService {

    private IFallaTecnicaRepository fallaTecnicaRepository;
    private IPersonaHumanaRepository personaHumanaRepository;
    private IHeladeraRepository heladeraRepository;

    private IBuscadorTecnico buscadorTecnico;

    // POST /falla-tecnica
    public FallaTecnicaOutputDTO reporteFallaTecnica(FallaTecnicaInputDTO fallaTecnicaInputDTO) {
        PersonaHumana colaborador = personaHumanaRepository.buscarPorId(fallaTecnicaInputDTO.getIdColaborador())
                .orElseThrow(() -> new PersonaHumanaNotFoundException("Colaborador no encontrado (id = "
                        + fallaTecnicaInputDTO.getIdColaborador() + ")"
                ));

        Heladera heladera = heladeraRepository.buscarPorId(fallaTecnicaInputDTO.getIdHeladera())
                .orElseThrow(() -> new HeladeraNotFoundException("Heladera no encontrada (id = "
                + fallaTecnicaInputDTO.getIdHeladera() + ")"
        ));

        FallaTecnica fallaTecnica = crearFallaTecnica(fallaTecnicaInputDTO);
        fallaTecnica.setColaborador(colaborador);
        fallaTecnica.setHeladera(heladera);
        fallaTecnicaRepository.guardar(fallaTecnica);

        asignarTecnico(fallaTecnica);
        return FallaTecnicaFactory.crearAPartirDe(fallaTecnica);
    }

    private void asignarTecnico(FallaTecnica fallaTecnica) {
        PuntoGeografico puntoGeografico = new PuntoGeografico();
        puntoGeografico.setLocalidad(fallaTecnica.getHeladera().getLocalidad());
        puntoGeografico.setLongitud(fallaTecnica.getHeladera().getLongitud());
        puntoGeografico.setLatitud(fallaTecnica.getHeladera().getLatitud());

        Tecnico tecnicoMasCercano = buscadorTecnico.buscarTecnicoMasCercano(puntoGeografico);
        fallaTecnica.setTecnicoAsignado(tecnicoMasCercano);
        this.notificarTecnico(tecnicoMasCercano, fallaTecnica);
    }

    // Podria ir en un factory?
    private FallaTecnica crearFallaTecnica(FallaTecnicaInputDTO fallaTecnicaInputDTO) {
        Optional<Heladera> heladera = heladeraRepository.buscarPorId(fallaTecnicaInputDTO.getIdHeladera());

        if (heladera.isEmpty()) {
            throw new HeladeraNotFoundException("Heladera no encontrada (id = "
                    + fallaTecnicaInputDTO.getIdHeladera() + ")"
            );
        }

        Optional<PersonaHumana> colaborador = personaHumanaRepository
                .buscarPorId(fallaTecnicaInputDTO.getIdColaborador());

        if (colaborador.isEmpty()) {
            throw new PersonaHumanaNotFoundException("Colaborador no encontrado (id = "
                    + fallaTecnicaInputDTO.getIdColaborador() + ")"
            );
        }

        FallaTecnica fallaTecnica = FallaTecnicaFactory.crearAPartirDe(fallaTecnicaInputDTO);
        fallaTecnica.setColaborador(colaborador.get());
        fallaTecnica.setHeladera(heladera.get());

        heladera.get().agregarFallaTecnica(fallaTecnica);

        return fallaTecnica;
    }

    private void notificarTecnico(Tecnico tecnico, FallaTecnica fallaTecnica) {
        Heladera heladera = fallaTecnica.getHeladera();
        String asunto = "La heladera " + heladera.getNombreSignificativo() + " tiene una falla tecnica.";
        String mensaje = "La heladera " + heladera.getNombreSignificativo() + "Ubicada en" +
                heladera.getDireccion() +
                heladera.getLocalidad().getNombre()
                + " tiene una falla tecnica. " +
                "Por favor, acuda a la heladera lo antes posible.";
        Notificacion notificacion = new Notificacion(asunto, mensaje);
        // enviar notificacion al tecnico
        tecnico.notificar(notificacion);
    }
}
