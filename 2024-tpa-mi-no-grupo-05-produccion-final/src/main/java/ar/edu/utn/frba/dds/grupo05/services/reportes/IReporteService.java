package ar.edu.utn.frba.dds.grupo05.services.reportes;

import java.util.List;

public interface IReporteService {
  public String obtenerRutaReporte(String nombreReporte);
  public List<String> obtenerReportes();
  }
