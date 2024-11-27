package ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.actions;

import ar.edu.utn.frba.dds.grupo05.services.sensores.ISensorMovimientoService;
import ar.edu.utn.frba.dds.grupo05.services.sensores.SensorMovimientoService;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.ReceptionAction;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MovementReceptionAction implements ReceptionAction {

    ISensorMovimientoService sensorMovimientoService;

    public MovementReceptionAction(ISensorMovimientoService sensorMovimientoService) {
        this.sensorMovimientoService = sensorMovimientoService;
    }

    @Override
    public void execute(MqttMessage mqttMessage) {
        Long idHeladera = Long.parseLong(mqttMessage.toString());
        sensorMovimientoService.registrarMovimiento(idHeladera);
        System.out.println("Se detecto movimiento en la heladera con id: " + idHeladera);
    }

}
