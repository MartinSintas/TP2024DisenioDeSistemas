package ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface ReceptionAction {
    void execute(MqttMessage mqttMessage);
}
