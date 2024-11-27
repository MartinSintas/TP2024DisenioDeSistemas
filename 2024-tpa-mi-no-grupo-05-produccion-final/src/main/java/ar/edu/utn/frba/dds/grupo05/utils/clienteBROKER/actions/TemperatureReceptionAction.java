package ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.actions;

import ar.edu.utn.frba.dds.grupo05.dtos.inputs.RegistroTemperaturaDTO;
import ar.edu.utn.frba.dds.grupo05.services.sensores.ISensorTemperaturaService;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.ReceptionAction;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public class TemperatureReceptionAction implements ReceptionAction {

    ISensorTemperaturaService sensorTemperaturaService;

    public TemperatureReceptionAction(ISensorTemperaturaService sensorTemperaturaService) {
        this.sensorTemperaturaService = sensorTemperaturaService;
    }

    @Override
    public void execute(MqttMessage mqttMessage) {
        RegistroTemperaturaDTO in = this.parseMessage(mqttMessage);
        sensorTemperaturaService.registrarTemperatura(in);
        // Print registro de temperautura
        System.out.println("Se registro la temperatura: " + in.getTemperatura() + " en el sensor: " + in.getIdSensor());
    }

    private RegistroTemperaturaDTO parseMessage(MqttMessage mqttMessage) {
        RegistroTemperaturaDTO in = new RegistroTemperaturaDTO();
        String[] id_temp = mqttMessage.toString().split(" ");
        in.setIdSensor(Long.valueOf(id_temp[0]));
        in.setTemperatura(Double.valueOf(id_temp[1]));
        return in;
    }
}
