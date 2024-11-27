package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Canje;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.ofertas.Oferta;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.CanjeDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.CanjeOutputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.OfertaOutputDTO;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaHumana;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.RepositorioPersonaJuridica;
import ar.edu.utn.frba.dds.grupo05.services.canje.ICanjeService;
import ar.edu.utn.frba.dds.grupo05.services.canje.IOfertaService;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class CanjeController  extends RoleBasedController{
    private ICanjeService canjeService;
    private RepositorioPersonaHumana personaHumanaRepository;
    private IOfertaService ofertaService;
    private RepositorioPersonaJuridica personaJuridicaRepository;

    public CanjeController(ICanjeService canjeService,
                           RepositorioPersonaHumana personaHumanaRepository,
                           IOfertaService ofertaService,
                           RepositorioPersonaJuridica personaJuridicaRepository) {
        this.canjeService = canjeService;
        this.personaHumanaRepository = personaHumanaRepository;
        this.ofertaService = ofertaService;
        this.personaJuridicaRepository = personaJuridicaRepository;
    }

    public void formulario(Context context) {
        Usuario user = context.sessionAttribute("usuario");
        Double puntos = canjeService.obtenerPuntos(user);
        List<Oferta> ofertas = this.ofertaService.obtenerOfertas(user);

        Map<String, Object> model = this.getModel(context);

        model.put("puntos", puntos);
        model.put("ofertas",ofertas);

        context.render("views/canjes/canjear-ofertas.hbs", model);

        //TODO: Hay que filtrar por categorias tmbn asi que ver como hacerlo

    }

    public void canjearOferta(Context context){
        //Gen al DTO y llamar al service

        String ofertaIdstr = context.formParam("ofertaId");
        String puntosStr = context.formParam("puntosNecesarios");

        Long ofertaId = ofertaIdstr != null ? Long.parseLong(ofertaIdstr) : null;
        Double puntos = puntosStr != null ? Double.parseDouble(puntosStr) : null;

        CanjeDTO canjeDTO = CanjeDTO.builder()
                .ofertaId(ofertaId)
                .puntosUtilizados(puntos)
                .build();

        Usuario usuario = context.sessionAttribute("usuario");

        CanjeOutputDTO canjeOutputDTO = canjeService.evaluarCanjeDeOferta(canjeDTO, usuario);

        Map<String, Object> model = this.getModel(context);

        model.put("canje", canjeOutputDTO);
        model.put("oferta", canjeOutputDTO.getOferta());

        context.render("views/canjes/canje-exitoso.hbs", model);
    }

    public void index(Context context) {
        Usuario usuario = context.sessionAttribute("usuario");

        List<Canje> canjes = canjeService.buscarCanjesPorUsuario(usuario);
        canjes.sort(Comparator.comparing(Canje::getFecha).reversed()); // Ordena del mas reciente arriba!

        Map<String, Object> model = this.getModel(context);
        model.put("canjes", canjes);

        context.render("views/canjes/canjes.hbs", model);
    }

    public void ofertasCanjeadas(Context context) {
        //Necesito los canjes que tiene como oferta las ofertas de mi Persona Juridica

        Usuario usuario = context.sessionAttribute("usuario");
        List<Canje> canjes = canjeService.obtenerCanjesRealizadosSobreOfertasDel(usuario);

        List<CanjeOutputDTO> canjesOutput = new ArrayList<>();
        for (Canje canje : canjes) {
            try {
                Optional<PersonaHumana> posiblePersonaHumana =  personaHumanaRepository.buscarPorUsuario(canje.getUsuario());
                if (posiblePersonaHumana.isPresent()){
                    canjesOutput.add(CanjeOutputDTO.builder()
                            .oferta(canje.getOferta())
                            .puntosUtilizados(canje.getPuntosUtilizados())
                            .fecha(canje.getFecha().toString())
                            .personaHumana(personaHumanaRepository.buscarPorUsuario(canje.getUsuario()).get())
                            .build());
                }
            } catch (Exception e){
                Optional<PersonaJuridica> posiblePersonaJuridica =  personaJuridicaRepository.buscarPorUsuario(canje.getUsuario());
                if (posiblePersonaJuridica.isPresent()){
                    canjesOutput.add(CanjeOutputDTO.builder()
                            .oferta(canje.getOferta())
                            .puntosUtilizados(canje.getPuntosUtilizados())
                            .fecha(canje.getFecha().toString())
                            .personaJuridica(personaJuridicaRepository.buscarPorUsuario(canje.getUsuario()).get())
                            .build());
                }
            }
        }

        Map<String, Object> model = this.getModel(context);
        model.put("canjes", canjesOutput);


        context.render("views/canjes/ofertas-canjeadas.hbs", model);
    }
}
