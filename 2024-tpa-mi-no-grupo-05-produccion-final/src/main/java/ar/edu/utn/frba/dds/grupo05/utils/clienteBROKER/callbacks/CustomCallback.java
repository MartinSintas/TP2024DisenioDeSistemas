package ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.callbacks;

import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.ReceptionAction;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class CustomCallback implements MqttCallback {

    private ReceptionAction receptionAction;

    public CustomCallback(ReceptionAction action) {
        this.receptionAction = action;
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Conexion perdida: " + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //System.out.println("Llego el mje. Topico: " + topic + " Mensaje: " + new String(message.getPayload()));
        this.receptionAction.execute(message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Delivery completado: " + token.isComplete());
    }
}
