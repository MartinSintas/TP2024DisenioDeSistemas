package ar.edu.utn.frba.dds.grupo05.exceptions;

import org.eclipse.paho.client.mqttv3.MqttException;

public class CouldNotDisconnectFromBrokerException extends RuntimeException {
    public CouldNotDisconnectFromBrokerException(MqttException e) {
        super(e);
    }
}
