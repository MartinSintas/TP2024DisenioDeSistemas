package ar.edu.utn.frba.dds.grupo05.utils.reportes.generador;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;

import javax.print.Doc;

public interface IAdapterPDF {
    //CRUD de PDF para si se cambia la biblioteca quede facil de mod
    public Document crearPDF(String path);

    public Paragraph crearParrafo(String text);
    public void agregarParrafo(Document document, Paragraph paragraph);

    public void agregarTitulo(Document document, String titulo);
    public void agregarChunk(Document document, Chunk chunk);

}