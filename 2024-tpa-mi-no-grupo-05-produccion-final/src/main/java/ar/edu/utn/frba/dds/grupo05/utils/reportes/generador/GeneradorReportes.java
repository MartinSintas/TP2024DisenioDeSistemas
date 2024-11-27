package ar.edu.utn.frba.dds.grupo05.utils.reportes.generador;

import ar.edu.utn.frba.dds.grupo05.utils.reportes.tiposReportes.Reporte;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GeneradorReportes {
    private String destinationPath;
    private IAdapterPDF iAdapterPDF;
    @Getter
    private List<Reporte> reportes;

    public GeneradorReportes(IAdapterPDF iAdapterPDF, Properties properties) {
        reportes = new ArrayList<>();
        this.iAdapterPDF = iAdapterPDF;
        this.destinationPath = properties.getProperty("pathReportes");
    }

    public void addReporte(Reporte reporte) {
        reportes.add(reporte);
    }
    public void generarPDF() {
        for (Reporte reporte : reportes) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
            Document doc = iAdapterPDF.crearPDF(destinationPath+reporte.getNombreDeArchivo()+LocalDateTime.now().format(dtf)+".pdf");
            iAdapterPDF.agregarTitulo(doc, reporte.getTitulo());
            Paragraph paragraph = iAdapterPDF.crearParrafo(reporte.getParrafos());
            iAdapterPDF.agregarParrafo(doc, paragraph);
            doc.close();
        }
    }
}
