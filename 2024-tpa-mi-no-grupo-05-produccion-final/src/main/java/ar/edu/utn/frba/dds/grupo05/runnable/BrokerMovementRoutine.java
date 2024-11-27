package ar.edu.utn.frba.dds.grupo05.runnable;

import ar.edu.utn.frba.dds.grupo05.config.BrokerProperties;
import ar.edu.utn.frba.dds.grupo05.config.ServiceLocator;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.ModeloHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.sensores.SensorMovimiento;
import ar.edu.utn.frba.dds.grupo05.exceptions.CouldNotConnectToBrokerException;
import ar.edu.utn.frba.dds.grupo05.exceptions.CouldNotSubscribeToTopicException;
import ar.edu.utn.frba.dds.grupo05.repositories.IAlertaRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.IHeladeraRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.ISensorMovimientoRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.alertas.AlertaListRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.heladeras.HeladerasListRepository;
import ar.edu.utn.frba.dds.grupo05.repositories.sensores.SensorMovimientoListRepository;
import ar.edu.utn.frba.dds.grupo05.services.sensores.ISensorMovimientoService;
import ar.edu.utn.frba.dds.grupo05.services.sensores.SensorMovimientoService;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.Broker;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.actions.MovementReceptionAction;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.callbacks.CustomCallback;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrokerMovementRoutine implements Runnable {
    @Override
    public void run() {
        BrokerProperties brokerProperties = ServiceLocator.getInstance(BrokerProperties.class);

        ISensorMovimientoRepository sensorMovimientoRepository = new SensorMovimientoListRepository();
        IAlertaRepository alertaRepository = new AlertaListRepository();
        IHeladeraRepository heladeraRepository = new HeladerasListRepository();

        // TODO:
        // Cuando tengamos persistencia en DB relacional, cambiar a las implementaciones de repo que se conecten a esta

        ModeloHeladera modelo1 = new ModeloHeladera();
        modelo1.setTemperaturaMaxima(5.0);
        modelo1.setTemperaturaMinima(2.0);
        Heladera heladera1 = new Heladera(modelo1);
        heladera1.setId(1L);
        SensorMovimiento sensor1 = new SensorMovimiento();
        sensor1.setId(1L);
        sensor1.setHeladera(heladera1);

        heladeraRepository.guardar(heladera1);
        sensorMovimientoRepository.save(sensor1);

        // heladera2, sensor2 , mismo modelo
        Heladera heladera2 = new Heladera(modelo1);
        heladera2.setId(2L);
        SensorMovimiento sensor2 = new SensorMovimiento();
        sensor2.setId(2L);
        sensor2.setHeladera(heladera2);

        heladeraRepository.guardar(heladera2);
        sensorMovimientoRepository.save(sensor2);

        //

        ISensorMovimientoService sensorMovimientoService = new SensorMovimientoService(
                sensorMovimientoRepository,
                heladeraRepository,
                alertaRepository
        );

        MovementReceptionAction action = new MovementReceptionAction(sensorMovimientoService);
        MqttCallback callback = new CustomCallback(action);
        Broker broker = new Broker(ServiceLocator.getInstance(BrokerProperties.class));
        broker.setCallback(callback);
        try {
            broker.connect();
        } catch (MqttException e) {
            throw new CouldNotConnectToBrokerException(e);
        }
        try {
            broker.subscribe(brokerProperties.getProperty("topic_movimiento"));
        } catch (MqttException e) {
            throw new CouldNotSubscribeToTopicException(e);
        }
    }
}
