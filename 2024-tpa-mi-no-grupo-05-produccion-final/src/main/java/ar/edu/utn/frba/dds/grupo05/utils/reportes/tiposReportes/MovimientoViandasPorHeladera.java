package ar.edu.utn.frba.dds.grupo05.utils.reportes.tiposReportes;

import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;

public class MovimientoViandasPorHeladera implements Reporte{
    private String titulo = "Movimiento de viandas por heladera";
    private String parrafosGenerados = "";
    private String nombreDeArchivo = "MovimientoViandasPorHeladera";
    private IHeladeraRepository heladeraRepository;

    public MovimientoViandasPorHeladera(IHeladeraRepository heladeraRepository) {
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
        if (this.heladeraRepository.buscarTodas().isEmpty()) {
            parrafosGenerados = "No hay heladeras registradas";
        }
        else{
            this.heladeraRepository.buscarTodas().forEach(heladera -> {
                parrafosGenerados += "Nombre heladera: " + heladera.getNombreSignificativo()
                        + " || Cantidad de movimientos: " + heladera.cantidadDeViandasDistribuidas() + "\n";
            });
        }
    }
}
