package ar.edu.utn.frba.dds.grupo05.exceptions;

import org.eclipse.paho.client.mqttv3.MqttException;

public class CouldNotSubscribeToTopicException extends RuntimeException {
    public CouldNotSubscribeToTopicException(MqttException e) {
        super(e);
    }
}
