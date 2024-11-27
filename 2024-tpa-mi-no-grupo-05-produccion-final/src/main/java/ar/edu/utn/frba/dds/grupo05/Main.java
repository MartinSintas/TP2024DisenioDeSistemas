package ar.edu.utn.frba.dds.grupo05;

import ar.edu.utn.frba.dds.grupo05.runnable.BrokerAperturaRoutine;
import ar.edu.utn.frba.dds.grupo05.runnable.BrokerMovementRoutine;
import ar.edu.utn.frba.dds.grupo05.runnable.BrokerTemperatureReceptionRoutine;
import ar.edu.utn.frba.dds.grupo05.server.DatabaseAux;
import ar.edu.utn.frba.dds.grupo05.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(Main.class);

        BrokerTemperatureReceptionRoutine brokerTemperatureReceptionRoutine = new BrokerTemperatureReceptionRoutine();
        Thread temperatureReceptionThread = new Thread(brokerTemperatureReceptionRoutine);
        temperatureReceptionThread.start();

        BrokerMovementRoutine brokerMovementRoutine = new BrokerMovementRoutine();
        Thread movementReceptionThread = new Thread(brokerMovementRoutine);
        movementReceptionThread.start();


        BrokerAperturaRoutine brokerAperturaRoutine = new BrokerAperturaRoutine();
        Thread aperturaReceptionThread = new Thread(brokerAperturaRoutine);
        aperturaReceptionThread.start();


        DatabaseAux dbAux = new DatabaseAux();
        dbAux.init();

        LOGGER.info("Inicializando base de datos...");


        Server.init();
        LOGGER.info("Sistema iniciado...");


    }
}
