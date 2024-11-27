package ar.edu.utn.frba.dds.grupo05.runnable;

import ar.edu.utn.frba.dds.grupo05.config.BrokerProperties;
import ar.edu.utn.frba.dds.grupo05.config.ServiceLocator;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.incidentes.Incidente;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorTemperatura;
import ar.edu.utn.frba.dds.grupo05.exceptions.CouldNotConnectToBrokerException;
import ar.edu.utn.frba.dds.grupo05.exceptions.CouldNotSubscribeToTopicException;
import ar.edu.utn.frba.dds.grupo05.repositories.IAlertaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.ISensorTemperaturaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.alertas.AlertaListRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.HeladerasListRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioAlerta;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.RepositorioSensorTemperatura;
import ar.edu.utn.frba.dds.grupo05.repositories.sensores.SensorTemperaturaListRepository;
import ar.edu.utn.frba.dds.grupo05.services.fallatecnica.IncidenteService;
import ar.edu.utn.frba.dds.grupo05.services.sensores.ISensorTemperaturaService;
import ar.edu.utn.frba.dds.grupo05.services.sensores.SensorTemperaturaService;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.Broker;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.ReceptionAction;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.actions.TemperatureReceptionAction;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.callbacks.CustomCallback;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrokerTemperatureReceptionRoutine implements Runnable {

    @Override
    public void run() {
        Properties brokerProperties = new Properties();

        // Cargar propiedades del broker
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.Broker.properties")) {
            if (input == null) {
                System.out.println("No se encontró el archivo config.Broker.properties");
                return;
            }
            brokerProperties.load(input);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo de configuración de broker: " + e.getMessage());
        }

        // Obtener instancias de repositorios y servicios desde el ServiceLocator
        RepositorioSensorTemperatura sensorTemperaturaRepository = ServiceLocator.getInstance(RepositorioSensorTemperatura.class);
        RepositorioAlerta alertaRepository = ServiceLocator.getInstance(RepositorioAlerta.class);
        RepositorioHeladera heladeraRepository = ServiceLocator.getInstance(RepositorioHeladera.class);
        IncidenteService incidenteService = ServiceLocator.getInstance(IncidenteService.class);

        // Crear servicio de sensor de temperatura con los repositorios
        ISensorTemperaturaService sensorTemperaturaService = new SensorTemperaturaService(
                sensorTemperaturaRepository,
                alertaRepository,
                heladeraRepository,
                incidenteService
        );

        // Configurar la acción de recepción y el callback de MQTT
        ReceptionAction action = new TemperatureReceptionAction(sensorTemperaturaService);
        MqttCallback callback = new CustomCallback(action);
        Broker broker = new Broker(ServiceLocator.getInstance(BrokerProperties.class));
        broker.setCallback(callback);

        try {
            broker.connect();
        } catch (MqttException e) {
            throw new CouldNotConnectToBrokerException(e);
        }

        try {
            broker.subscribe(brokerProperties.getProperty("topic_temperatura"));
        } catch (MqttException e) {
            throw new CouldNotSubscribeToTopicException(e);
        }
    }
}

