package ar.edu.utn.frba.dds.grupo05.exceptions;

import org.eclipse.paho.client.mqttv3.MqttException;

public class CouldNotConnectToBrokerException extends RuntimeException {
    public CouldNotConnectToBrokerException(MqttException e) {
        super(e);
    }
}
