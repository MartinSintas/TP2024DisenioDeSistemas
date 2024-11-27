package ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.actions;

import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.ReceptionAction;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PrintReceptionAction implements ReceptionAction {
    @Override
    public void execute(MqttMessage mqttMessage) {
        System.out.println("Mensaje recibido: " + mqttMessage.toString());
    }
}
