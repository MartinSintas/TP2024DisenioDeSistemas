package ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER;

import ar.edu.utn.frba.dds.grupo05.config.BrokerProperties;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class Broker {
    private MemoryPersistence persistence = new MemoryPersistence();
    private MqttClient client;
    private String broker;
    private String clientId;

    @Setter
    private MqttCallback callback;

    private final Logger LOGGER = LoggerFactory.getLogger(Broker.class);

    public Broker(BrokerProperties brokerProperties) {
        this.broker = brokerProperties.getProperty("broker");
        this.clientId = UUID.randomUUID().toString();
    }

    public void connect() throws MqttException {
        client = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);

        try {
            System.out.println("Conectando al broker: " + broker);
            client.connect(connOpts);
            System.out.println("Conectado");

            client.setCallback(this.callback);

        } catch (MqttException me) {
            System.out.println("Razon: " + me.getReasonCode());
            System.out.println("Mensaje: " + me.getMessage());
            System.out.println("Mensaje localizado: " + me.getLocalizedMessage());
            System.out.println("Causa: " + me.getCause());
            System.out.println("Excepcion: " + me);
            me.printStackTrace();
        }
    }

    public void subscribe(String topic) throws MqttException {
        client.subscribe(topic);
        System.out.println("Suscrito al topico: " + topic);
    }

    public void publish(String topic, String content, int qos) throws MqttException {
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        client.publish(topic, message);
        System.out.println("Mensaje publicado en el topico: " + topic);
    }

    public void disconnect() throws MqttException {
        if (client != null && client.isConnected()) {
            client.disconnect();
            System.out.println("Desconectado del broker: " + broker);
        }
    }


}
