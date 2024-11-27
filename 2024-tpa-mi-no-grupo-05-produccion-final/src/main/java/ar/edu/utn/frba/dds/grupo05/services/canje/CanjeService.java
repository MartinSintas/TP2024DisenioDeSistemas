package ar.edu.utn.frba.dds.grupo05.services.canje;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Canje;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Oferta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.TipoRol;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.CanjeDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.CanjeOutputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.OfertaOutputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.OfertaNotFoundException;
import ar.edu.utn.frba.dds.grupo05.exceptions.PuntosInsuficientesException;
import ar.edu.utn.frba.dds.grupo05.exceptions.UsuarioNoVinculadoAPersonaException;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.ofertas.RepositorioCanje;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.ofertas.RepositorioOferta;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaHumana;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaJuridica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CanjeService implements ICanjeService{
    private RepositorioPersonaHumana personaHumanaRepository;
    private RepositorioOferta ofertaRepository;
    private RepositorioCanje canjeRepository;
    private RepositorioPersonaJuridica personaJuridicaRepository;
    private OfertaService ofertaService;

    public CanjeService(RepositorioPersonaHumana personaHumanaRepository,
                        RepositorioOferta ofertaRepository,
                        RepositorioCanje canjeRepository,
                        RepositorioPersonaJuridica personaJuridicaRepository,
                        OfertaService ofertaService) {
        this.personaHumanaRepository = personaHumanaRepository;
        this.ofertaRepository = ofertaRepository;
        this.canjeRepository = canjeRepository;
        this.personaJuridicaRepository = personaJuridicaRepository;
        this.ofertaService = ofertaService;
    }

    @Override
    public CanjeOutputDTO evaluarCanjeDeOferta(CanjeDTO canjeDTO, Usuario usuario) {
        Boolean sePuedeCanjear = false;

        if(usuario.getRol().equals(TipoRol.PERSONA_HUMANA)){
            sePuedeCanjear = evaluarCanjePersonaHumana(canjeDTO, usuario);
        } else {
            sePuedeCanjear = evaluarCanjePersonaJuridica(canjeDTO, usuario);
        }

        Oferta ofertaACanjear = null;
        LocalDateTime fechaCanje = null;

        if (sePuedeCanjear) {
            ofertaACanjear = ofertaRepository.buscarPorId(canjeDTO.getOfertaId())
                    .orElseThrow(() -> new OfertaNotFoundException("Heladera no encontrada (id = "
                            + canjeDTO.getOfertaId() + ")"));

            Canje canje = Canje.builder()
                    .oferta(ofertaACanjear)
                    .puntosUtilizados(canjeDTO.getPuntosUtilizados())
                    .fecha(LocalDateTime.now())
                    .usuario(usuario)
                    .build();

            fechaCanje = canje.getFecha();

            ofertaACanjear.agregarCanje(canje);

            ofertaRepository.actualizarOferta(ofertaACanjear);

            canjeRepository.guardarCanje(canje);

            canjear(canje, usuario);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

       CanjeOutputDTO canjeOutputDTO = CanjeOutputDTO.builder()
                .puntosUtilizados(canjeDTO.getPuntosUtilizados())
                .oferta(ofertaACanjear)
                .fecha(fechaCanje.format(formatter))
                .build();

        return canjeOutputDTO;
    }

    private void canjear(Canje canje, Usuario usuario) {
        if(usuario.getRol().equals(TipoRol.PERSONA_HUMANA)){
            PersonaHumana personaHumana = personaHumanaRepository.buscarPorUsuario(usuario).orElseThrow(
                    UsuarioNoVinculadoAPersonaException::new);
            personaHumana.descontarPuntos(canje.getPuntosUtilizados());
            personaHumana.agregarCanje(canje);
            personaHumanaRepository.actualizarPersonaHumana(personaHumana);

        } else {
            PersonaJuridica personaJuridica = personaJuridicaRepository.buscarPorUsuario(usuario).orElseThrow(
                    UsuarioNoVinculadoAPersonaException::new);
            personaJuridica.descontarPuntos(canje.getPuntosUtilizados());
            personaJuridica.agregarCanje(canje);
            personaJuridicaRepository.actualizarPersonaJuridica(personaJuridica);
        }
    }

    private Boolean evaluarCanjePersonaHumana(CanjeDTO canjeDTO, Usuario usuario) {
        PersonaHumana personaHumana = personaHumanaRepository.buscarPorUsuario(usuario).orElseThrow(
                UsuarioNoVinculadoAPersonaException::new);

        if(personaHumana.getPuntosAcumulados() < canjeDTO.getPuntosUtilizados()){
            throw new PuntosInsuficientesException();
        }
        else {
            return true;
        }
    }

    private Boolean evaluarCanjePersonaJuridica(CanjeDTO canjeDTO, Usuario usuario) {
        PersonaJuridica personaJuridica = personaJuridicaRepository.buscarPorUsuario(usuario).orElseThrow(
                UsuarioNoVinculadoAPersonaException::new);

        if(personaJuridica.getPuntosAcumulados() < canjeDTO.getPuntosUtilizados()){
            throw new PuntosInsuficientesException();
        }
        else {
            return true;
        }
    }

    public Double obtenerPuntos(Usuario usuario){
        Double puntos = -1.0;
        if (usuario.getRol().equals(TipoRol.PERSONA_HUMANA)) {
            puntos = obtenerPuntosPersonaHumana(usuario);
        } else {
            puntos = obtenerPuntosPersonaJuridica(usuario);
        }
        return puntos;
    }

    private Double obtenerPuntosPersonaJuridica(Usuario usuario) {
        PersonaJuridica personaJuridica = personaJuridicaRepository.buscarPorUsuario(usuario).orElseThrow(
                UsuarioNoVinculadoAPersonaException::new
        );

        return personaJuridica.getPuntosAcumulados();
    }

    private Double obtenerPuntosPersonaHumana(Usuario usuario) {
        PersonaHumana personaHumana = personaHumanaRepository.buscarPorUsuario(usuario).orElseThrow(
                UsuarioNoVinculadoAPersonaException::new
        );

        return personaHumana.getPuntosAcumulados();
    }

    @Override
    public List<Canje> buscarCanjesPorUsuario(Usuario usuario) {
        if(usuario.getRol().equals(TipoRol.PERSONA_HUMANA)){
            PersonaHumana personaHumana = personaHumanaRepository.buscarPorUsuario(usuario).orElseThrow(
                    UsuarioNoVinculadoAPersonaException::new
            );

            return personaHumana.getCanjesRealizados();
        } else {
            PersonaJuridica personaJuridica = personaJuridicaRepository.buscarPorUsuario(usuario).orElseThrow(
                    UsuarioNoVinculadoAPersonaException::new
            );

            return personaJuridica.getCanjesRealizados();
        }
    }

    @Override
    public List<Canje> obtenerCanjesRealizadosSobreOfertasDel(Usuario usuario) {
        List<OfertaOutputDTO> ofertasDeUsuario = this.ofertaService.obtenerOfertasDisponibles(usuario); // ofertas dispoibles del usuario juridico

        return ofertasDeUsuario.stream().flatMap(oferta -> oferta.getCanjes().stream()).toList();
    }
}
