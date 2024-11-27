package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.DistribucionVianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.viandas.Vianda;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.EstadoHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.viandas.RepositorioDistribucionVianda;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.HeladerasListRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioHeladera;
import ar.edu.utn.frba.dds.grupo05.services.DistribucionViandaService;
import ar.edu.utn.frba.dds.grupo05.services.heladera.IHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.personashumanasservices.PersonaHumanaService;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DistribucionViandasController extends RoleBasedController{

  private DistribucionViandaService distribucionViandaService;
  private IHeladeraService heladeraService;


  public DistribucionViandasController(DistribucionViandaService distribucionViandaService,
                                       IHeladeraService heladeraService) {
    this.distribucionViandaService = distribucionViandaService;
    this.heladeraService = heladeraService;
  }



  public void create(Context context) {
    List<HeladeraOutputDTO> heladeras = heladeraService.obtenerTodasPorEstado(EstadoHeladera.ACTIVA);
    Map<String, Object> model = this.getModel(context);
    model.put("heladeras", heladeras);
    context.render("views/colaboraciones/formularios/formulario-distribucion-vianda.hbs", model);
  }


  public void save(Context context) {
    Long heladeraOrigenId = Long.parseLong(context.formParam("originFridgeId"));
    Long heladeraDestinoId = Long.parseLong(context.formParam("destinationFridgeId"));
    String motivoDistribucion = context.formParam("redistributionReason");
    Integer cantidadViandas = Integer.parseInt(context.formParam("viandaCount"));

    Heladera heladeraOrigen = heladeraService.obtenerPorId(heladeraOrigenId);
    Heladera heladeraDestino = heladeraService.obtenerPorId(heladeraDestinoId);


    heladeraOrigen.setCapacidadEnViandas(heladeraOrigen.cantidadViandas() - cantidadViandas);
    heladeraDestino.setCapacidadEnViandas(heladeraDestino.cantidadViandas() + cantidadViandas);
    DistribucionVianda distribucionVianda = DistribucionVianda.builder()
        .heladeraOrigen(heladeraOrigen)
        .heladeraDestino(heladeraDestino)
        .motivoDistribucion(motivoDistribucion)
        .fecha(LocalDateTime.now())
        // .viandas(viandasAMover) habria que agarrar viandas de la heladera origen y moverlas a la destino
        .build();

    heladeraService.actualizar(heladeraDestino);
    heladeraService.actualizar(heladeraOrigen);
    distribucionViandaService.registrarDistribucionVianda(distribucionVianda, context.sessionAttribute("usuario"));
    registroExito(context);
  }



  private void registroExito(Context context) {
    String titulo = "Distribución exitosa";
    String mensaje = "La distribución de viandas fue registrada exitosamente.";
    context.render("views/estados/success.hbs", Map.of("titulo", titulo, "mensaje", mensaje));
  }



}
