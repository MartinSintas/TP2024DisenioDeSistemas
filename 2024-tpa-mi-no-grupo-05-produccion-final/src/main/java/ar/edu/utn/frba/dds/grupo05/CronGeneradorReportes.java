package ar.edu.utn.frba.dds.grupo05;

import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.HeladerasListRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.IPersonaHumanaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.personas.PersonaHumanaListRepository;
import ar.edu.utn.frba.dds.grupo05.utils.reportes.generador.AdapterITextPDF;
import ar.edu.utn.frba.dds.grupo05.utils.reportes.generador.GeneradorReportes;
import ar.edu.utn.frba.dds.grupo05.utils.reportes.generador.IAdapterPDF;
import ar.edu.utn.frba.dds.grupo05.utils.reportes.tiposReportes.FallasPorHeladera;
import ar.edu.utn.frba.dds.grupo05.utils.reportes.tiposReportes.MovimientoViandasPorHeladera;
import ar.edu.utn.frba.dds.grupo05.utils.reportes.tiposReportes.Reporte;
import ar.edu.utn.frba.dds.grupo05.utils.reportes.tiposReportes.ViandasPorColaborador;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CronGeneradorReportes {
    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/app.properties";

        Properties appProps = new Properties();
        appProps.load(new FileInputStream(path));

        IHeladeraRepository heladeraListRepo = new HeladerasListRepository();
        IPersonaHumanaRepository personaListRepo = new PersonaHumanaListRepository();

        Reporte fallasPorHeladera = new FallasPorHeladera(heladeraListRepo);
        Reporte movimientosViandasPorHeladera = new MovimientoViandasPorHeladera(heladeraListRepo);
        Reporte viandasPorColaborador = new ViandasPorColaborador(personaListRepo);

        IAdapterPDF adapterPDF = new AdapterITextPDF();
        GeneradorReportes generadorReportes = new GeneradorReportes(adapterPDF, appProps);
        generadorReportes.addReporte(fallasPorHeladera);
        generadorReportes.addReporte(movimientosViandasPorHeladera);
        generadorReportes.addReporte(viandasPorColaborador);
        generadorReportes.generarPDF();
    }
}
