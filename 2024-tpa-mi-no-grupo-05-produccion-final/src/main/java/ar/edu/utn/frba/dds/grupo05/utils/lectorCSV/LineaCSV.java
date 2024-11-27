package ar.edu.utn.frba.dds.grupo05.utils.lectorCSV;


import lombok.Getter;

@Getter
public class LineaCSV {
    private final String tipoDocumento;
    private final String numeroDocumento;
    private final String nombre;
    private final String apellido;
    private final String email;
    private final String fechaColaboracion;
    private final String formaColaboracion;
    private final Integer cantidad;

    public LineaCSV(String tipoDocumento, String numeroDocumento, String nombre, String apellido, String email, String fechaColaboracion, String formaColaboracion, Integer cantidad) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaColaboracion = fechaColaboracion;
        this.formaColaboracion = formaColaboracion;
        this.cantidad = cantidad;
    }
}
