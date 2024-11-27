package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.domain.entities.usuarios.Usuario;
import ar.edu.utn.frba.dds.grupo05.dtos.DonacionDineroDTO;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.dinero.RepositorioDonacionDinero;
import ar.edu.utn.frba.dds.grupo05.repositories.colaboraciones.dinero.RepositorioDonacionDineroProgramada;
import ar.edu.utn.frba.dds.grupo05.services.donaciondinero.IDonacionDineroService;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class DonacionDineroController extends RoleBasedController{

    IDonacionDineroService donacionDineroService;

    public DonacionDineroController(IDonacionDineroService donacionDineroService) {
        this.donacionDineroService = donacionDineroService;
    }


    public void formulario(Context context) {
        context.render("views/colaboraciones/formularios/formulario-donacion-dinero.hbs");
    }

    public void hacerDonacion(Context context) {

        Boolean recurrente = "recurring".equals(context.formParam("frequency"));
        Integer diasParaRepetir = context.formParam("days") != "" ? Integer.valueOf(context.formParam("days")) : null;

        DonacionDineroDTO donacionDineroDTO = DonacionDineroDTO
                .builder()
                .monto(Double.valueOf(context.formParam("amount")))
                .recurrente(recurrente)
                .diasParaRepetir(diasParaRepetir)
                .build();

        Usuario usuario = context.sessionAttribute("usuario");
        DonacionDineroDTO output = donacionDineroService.donarDinero(donacionDineroDTO, usuario);

        Map<String, Object> model = this.getModel(context);
        model.put("monto", output.getMonto().toString());
        model.put("recurrente", output.getRecurrente());
        model.put("diasParaRepetir", output.getDiasParaRepetir());

        context.render("views/colaboraciones/formularios/estados/donacion-dinero-exitosa.hbs", model);
    }
}
