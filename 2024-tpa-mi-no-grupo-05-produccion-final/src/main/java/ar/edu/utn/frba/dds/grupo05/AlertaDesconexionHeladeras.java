package ar.edu.utn.frba.dds.grupo05;

import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.RegistroTemperatura;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tecnicos.Tecnico;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.Localidad;
import ar.edu.utn.frba.dds.grupo05.domain.entities.ubicacion.PuntoGeografico;
import ar.edu.utn.frba.dds.grupo05.repositories.IAlertaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.alertas.AlertaListRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.HeladerasListRepository;
import ar.edu.utn.frba.dds.grupo05.services.verificadorconexionheladeras.VerificadorConexionHeladeras;

import java.time.LocalDateTime;

public class AlertaDesconexionHeladeras {
    public static void main(String[] args) {

        try {
            Integer.valueOf(args[0]);
        } catch(Exception e) {
            throw new IllegalArgumentException("Se debe ingresar un argumento numero entero");
        }

        IHeladeraRepository heladeraRepository = null;
        IAlertaRepository alertaRepository = null;

        //Parte de "Mock"

        heladeraRepository = new HeladerasListRepository();
        alertaRepository = new AlertaListRepository();

        PuntoGeografico puntoGeograficoConTecnicos = new PuntoGeografico("-34", "59");
        Localidad localidad = new Localidad("La Matanza");
        puntoGeograficoConTecnicos.setLocalidad(localidad);
        puntoGeograficoConTecnicos.setLatitud("-34");
        puntoGeograficoConTecnicos.setLongitud("-59");

        Tecnico tecnico1 = new Tecnico("Juan",
                "Perez",
                null,
                "123456789",
                null,
                null);

        tecnico1.setUbicacion(new PuntoGeografico("-34.603722", "-58.381592"));

        Tecnico tecnico2 = new Tecnico("Pedro",
                "Gomez",
                null,
                "987654321",
                null,
                null);

        tecnico2.setUbicacion(new PuntoGeografico("-34.603722", "-59.381592"));

        localidad.addTecnico(tecnico2);

        Tecnico tecnico3 = new Tecnico("Carlos",
                "Gonzalez",
                null,
                "123456789",
                null,
                null);

        tecnico3.setUbicacion(new PuntoGeografico("-34.603722", "-60.381592"));

        localidad.addTecnico(tecnico3);

        Heladera heladera1 = new Heladera();
        //heladera1.setUbicacion(puntoGeograficoConTecnicos);
        heladera1.setNombreSignificativo("Heladera 1");
        heladera1.setId(1L);
        RegistroTemperatura registroTemperatura1 = new RegistroTemperatura();
        registroTemperatura1.setFecha(LocalDateTime.now());
        heladera1.setUltimaTemperaturaRegistrada(registroTemperatura1);

        // Heladera con conexion caida
        Heladera heladera2 = new Heladera();
        //heladera2.setUbicacion(puntoGeograficoConTecnicos);
        heladera2.setNombreSignificativo("Heladera 2");
        heladera2.setId(2L);
        RegistroTemperatura registroTemperatura2 = new RegistroTemperatura();
        registroTemperatura2.setFecha(LocalDateTime.now().minusMinutes(20));
        heladera2.setUltimaTemperaturaRegistrada(registroTemperatura2);


        Heladera heladera3 = new Heladera();
        //heladera3.setUbicacion(puntoGeograficoConTecnicos);
        heladera3.setNombreSignificativo("Heladera 3");
        heladera3.setId(3L);
        RegistroTemperatura registroTemperatura3 = new RegistroTemperatura();
        registroTemperatura3.setFecha(LocalDateTime.now());
        heladera3.setUltimaTemperaturaRegistrada(registroTemperatura3);

        heladeraRepository.guardar(heladera1);
        heladeraRepository.guardar(heladera2);
        heladeraRepository.guardar(heladera3);


        // Fin parte de mock

        VerificadorConexionHeladeras verificador = new VerificadorConexionHeladeras(
                heladeraRepository,
                alertaRepository
        );

        verificador.generarAlertaHeladerasSinConexion(Integer.valueOf(args[0]));

        System.out.println("Alertas generadas: " + alertaRepository.buscarTodas().size());
    }
}
