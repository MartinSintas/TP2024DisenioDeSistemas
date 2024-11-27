package ar.edu.utn.frba.dds.grupo05.exceptions;

import org.eclipse.paho.client.mqttv3.MqttException;

public class CouldNotPublishMessageException extends RuntimeException {
    public CouldNotPublishMessageException(MqttException e) {
        super(e);
    }
}
