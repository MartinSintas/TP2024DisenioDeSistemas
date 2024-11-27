package ar.edu.utn.frba.dds.grupo05.utils.reportes.tiposReportes;

import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;

import java.util.List;

public class FallasPorHeladera implements Reporte{

    private String titulo = "Fallas por Heladera";
    private String parrafosGenerados = "";
    private String nombreDeArchivo = "FallasPorHeladera";

    private IHeladeraRepository heladeraRepository;

    public FallasPorHeladera(IHeladeraRepository heladeraRepository) {
        this.heladeraRepository = heladeraRepository;
    }

    @Override
    public String getTitulo() {
        return this.titulo;
    }

    @Override
    public String getParrafos() {
        this.generarParrafos();
        return this.parrafosGenerados;
    }

    @Override
    public String getNombreDeArchivo() {
        return this.nombreDeArchivo;
    }

    public void generarParrafos(){
        parrafosGenerados = "";
        if (this.heladeraRepository.buscarTodas().isEmpty())
            this.parrafosGenerados = "No hay heladeras registradas";
        else{
            this.heladeraRepository.buscarTodas().forEach(heladera -> {
                parrafosGenerados += "Nombre heladera: " + heladera.getNombreSignificativo() + " || Cantidad de fallas: " + heladera.cantidadDeFallasTecnicas() + "\n";
            });
        }
    }

}
