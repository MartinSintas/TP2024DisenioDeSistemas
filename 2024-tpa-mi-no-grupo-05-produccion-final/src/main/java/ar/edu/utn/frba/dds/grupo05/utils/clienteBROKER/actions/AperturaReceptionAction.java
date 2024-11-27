package ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.actions;

import ar.edu.utn.frba.dds.grupo05.config.ObjectMapperConfig;
import ar.edu.utn.frba.dds.grupo05.config.ServiceLocator;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.AperturaHeladeraInputDTO;
import ar.edu.utn.frba.dds.grupo05.services.aperturaheladera.AperturaHeladeraService;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.ReceptionAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class AperturaReceptionAction implements ReceptionAction {

    ObjectMapper objectMapper;
    AperturaHeladeraService aperturaHeladeraService = ServiceLocator.getInstance(AperturaHeladeraService.class);
    Logger LOGGER = LoggerFactory.getLogger(AperturaReceptionAction.class);

    public AperturaReceptionAction() {
        this.objectMapper = ObjectMapperConfig.createObjectMapper();
    }

    @Override
    public void execute(MqttMessage mqttMessage) {
        try {
            AperturaHeladeraInputDTO aperturaRealizada = objectMapper.readValue(mqttMessage.toString(), AperturaHeladeraInputDTO.class);
            aperturaHeladeraService.recibirRespuestaAperturaHeladera(aperturaRealizada);
        } catch (Exception e) {
            LOGGER.error("Error al deserializar el mensaje", e);
        }
    }
}
