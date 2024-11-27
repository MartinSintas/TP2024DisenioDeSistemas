package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.dtos.DonacionDineroDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.DonacionViandaDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.grupo05.services.IDonacionViandaService;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DonacionViandasController extends RoleBasedController {
  IDonacionViandaService donacionViandaService;

  public DonacionViandasController(IDonacionViandaService donacionViandaService) {
    this.donacionViandaService = donacionViandaService;
  }

  public void create(Context context) {

    Map<String, Object> model = this.getModel(context);

    List<HeladeraOutputDTO> heladeras = donacionViandaService.obtenerPosiblesHeladeras();
    model.put("heladeras", heladeras);

    context.render("views/colaboraciones/formularios/formulario-donacion-vianda.hbs", model);
  }

  public void save(Context context) {
    DonacionViandaDTO donacionViandaDTO = DonacionViandaDTO.builder()
            .heladeraId(context.formParamAsClass("fridgeId", Long.class).getOrThrow(
                    ValidationException::new
            ))
            .descripcion(context.formParamAsClass("foodDescription", String.class).getOrDefault(null))
            .fechaDeCaducidad(LocalDate.parse(
                    context.formParamAsClass("expirationDate", String.class).getOrThrow(
                            ValidationException::new
                    )
            ))
            .kiloCalorias(context.formParamAsClass("calories", Integer.class).getOrDefault(
                    null
            ))
            .pesoEnGramos(context.formParamAsClass("weight", Integer.class).getOrDefault(
                    null
            ))
            .usuario(context.sessionAttribute("usuario"))
            .build();

    this.donacionViandaService.donarVianda(donacionViandaDTO);
    Integer tiempoParaApertura = this.donacionViandaService
            .obtenerTiempoParaApertura(donacionViandaDTO.getHeladeraId());

    Map<String, Object> model = this.getModel(context);

    model.put("titulo", "Donación de vianda registrada con éxito");

    model.put("mensaje",
     "La donación de vianda ha sido registrada con éxito. Tienes " +
             tiempoParaApertura +
             " horas para entregarla en la heladera seleccionada.");

    context.render("views/estados/success.hbs", model);
  }

}
