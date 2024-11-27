package ar.edu.utn.frba.dds.grupo05.utils.reportes.generador;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class AdapterITextPDF implements IAdapterPDF {

    @Override
    public Document crearPDF(String path) {
        Document document = new Document();
        try {
            PdfWriter.getInstance
                    (document, new FileOutputStream(path));
            document.open();

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return document;
    }

    @Override
    public Paragraph crearParrafo(String text) {
        return new Paragraph(text);
    }

    @Override
    public void agregarParrafo(Document document, Paragraph paragraph) {
        try {
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void agregarTitulo(Document document, String titulo) {
        Chunk chunk = new Chunk(titulo, new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD));
        agregarChunk(document, chunk);
    }

    @Override
    public void agregarChunk(Document document, Chunk chunk) {
        try {
            document.add(chunk);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
