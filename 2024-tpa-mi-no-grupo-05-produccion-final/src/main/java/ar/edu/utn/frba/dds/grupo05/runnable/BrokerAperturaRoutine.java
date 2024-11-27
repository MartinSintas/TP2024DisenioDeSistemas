package ar.edu.utn.frba.dds.grupo05.runnable;

import ar.edu.utn.frba.dds.grupo05.config.BrokerProperties;
import ar.edu.utn.frba.dds.grupo05.config.ServiceLocator;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorMovimiento;
import ar.edu.utn.frba.dds.grupo05.exceptions.CouldNotConnectToBrokerException;
import ar.edu.utn.frba.dds.grupo05.exceptions.CouldNotSubscribeToTopicException;
import ar.edu.utn.frba.dds.grupo05.repositories.IAlertaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.IAperturaHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.ISensorMovimientoRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.alertas.AlertaListRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.HeladerasListRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.sensores.SensorMovimientoListRepository;
import ar.edu.utn.frba.dds.grupo05.services.aperturaheladera.AperturaHeladeraService;
import ar.edu.utn.frba.dds.grupo05.services.sensores.ISensorMovimientoService;
import ar.edu.utn.frba.dds.grupo05.services.sensores.SensorMovimientoService;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.Broker;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.actions.AperturaReceptionAction;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.actions.MovementReceptionAction;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.callbacks.CustomCallback;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrokerAperturaRoutine implements Runnable {
    @Override
    public void run() {
        BrokerProperties brokerProperties = ServiceLocator.getInstance(BrokerProperties.class);

        AperturaHeladeraService aperturaHeladeraService = ServiceLocator.getInstance(AperturaHeladeraService.class);

        AperturaReceptionAction action = new AperturaReceptionAction();
        MqttCallback callback = new CustomCallback(action);
        Broker broker = new Broker(ServiceLocator.getInstance(BrokerProperties.class));
        broker.setCallback(callback);
        try {
            broker.connect();
        } catch (MqttException e) {
            throw new CouldNotConnectToBrokerException(e);
        }
        try {
            broker.subscribe(brokerProperties.getProperty("topic_aperturas_realizadas"));
        } catch (MqttException e) {
            throw new CouldNotSubscribeToTopicException(e);
        }
    }
}
