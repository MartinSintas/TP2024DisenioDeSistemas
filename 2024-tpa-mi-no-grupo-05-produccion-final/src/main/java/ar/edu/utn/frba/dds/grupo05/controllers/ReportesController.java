package ar.edu.utn.frba.dds.grupo05.controllers;

import ar.edu.utn.frba.dds.grupo05.services.reportes.IReporteService;
import ar.edu.utn.frba.dds.grupo05.services.reportes.ReporteService;

import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportesController extends RoleBasedController {

  private IReporteService reportesService;

  public ReportesController(IReporteService reportesService) {
    this.reportesService = reportesService;
  }


  public void listarReportes(Context context) {
    List<String> reportes = reportesService.obtenerReportes();

    Map<String, Object> model = this.getModel(context);
    model.put("reportes", reportes);
    context.render("views/reportes/lista_reportes.hbs", model);
  }

  public void descargarReporte(Context context) {
    String nombreReporte = context.pathParam("nombreReporte");
    String rutaReporte = reportesService.obtenerRutaReporte(nombreReporte);

    if (rutaReporte == null) {
      context.status(404).result("Reporte no encontrado");
      return;
    }


    context.result(rutaReporte)
        .header("Content-Disposition", "attachment; filename=" + nombreReporte + ".pdf");
  }
}


