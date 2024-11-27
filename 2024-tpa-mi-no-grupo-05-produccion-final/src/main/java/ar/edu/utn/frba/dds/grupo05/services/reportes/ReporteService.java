package ar.edu.utn.frba.dds.grupo05.services.reportes;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ReporteService implements IReporteService {


  private String pathReportes;

  public ReporteService(Properties properties) {

    this.pathReportes = properties.getProperty("pathReportes");
  }

@Override
  public String obtenerRutaReporte(String nombreReporte) {
    return pathReportes + nombreReporte + ".pdf";
  }

@Override
  public List<String> obtenerReportes() {
    List<String> reportes = new ArrayList<>();
    File folder = new File(pathReportes);

    if (folder.exists() && folder.isDirectory()) {
      File[] files = folder.listFiles((dir, name) -> name.endsWith(".pdf")); // Filtramos los .pdf

      if (files != null) {
        for (File file : files) {
          reportes.add(file.getName());
        }
      }
    }
    return reportes;
  }

}