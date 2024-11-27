package ar.edu.utn.frba.dds.grupo05.services.aperturaheladera;

import ar.edu.utn.frba.dds.grupo05.config.BrokerProperties;
import ar.edu.utn.frba.dds.grupo05.domain.entities.colaboraciones.solicitudesApertura.factories.AperturaHeladeraFactory;
import ar.edu.utn.frba.dds.grupo05.domain.entities.heladeras.Heladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.personas.PersonaHumana;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.AperturaHeladera;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.EstadoApertura;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.MotivoApertura;
import ar.edu.utn.frba.dds.grupo05.domain.entities.tarjetas.colaboradores.TarjetaColaborador;
import ar.edu.utn.frba.dds.grupo05.dtos.inputs.AperturaHeladeraInputDTO;
import ar.edu.utn.frba.dds.grupo05.dtos.outputs.AperturaHeladeraBrokerOutputDTO;
import ar.edu.utn.frba.dds.grupo05.exceptions.*;
import ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.colaboradores.RepositorioAperturaHeladera;
import ar.edu.utn.frba.dds.grupo05.repositories.tarjetas.colaboradores.RepositorioTarjetaColaborador;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.Broker;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.ReceptionAction;
import ar.edu.utn.frba.dds.grupo05.utils.clienteBROKER.callbacks.CustomCallback;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;

public class AperturaHeladeraService implements IAperturaHeladeraService {
    private RepositorioAperturaHeladera aperturaHeladeraRepository;
    private RepositorioTarjetaColaborador repositorioTarjetaColaborador;
    private Broker broker;
    Properties brokerProperties;
    private final Logger logger = LoggerFactory.getLogger(AperturaHeladeraService.class);

    public AperturaHeladeraService(RepositorioAperturaHeladera aperturaHeladeraRepository,
                                   RepositorioTarjetaColaborador repositorioTarjetaColaborador,
                                   Broker broker,
                                   BrokerProperties brokerProperties) {
        this.aperturaHeladeraRepository = aperturaHeladeraRepository;
        this.repositorioTarjetaColaborador = repositorioTarjetaColaborador;
        this.broker = broker;
        this.brokerProperties = brokerProperties;

        ReceptionAction action = new ReceptionAction() {
            @Override
            public void execute(MqttMessage mqttMessage) {
                System.out.println("Mensaje recibido: " + mqttMessage.toString());
            }
        };

        CustomCallback customCallback = new CustomCallback(action);

        this.broker.setCallback(customCallback);
    }

    public void guardarApertura(PersonaHumana personaHumana,
                                TarjetaColaborador tarjetaColaborador,
                                Heladera heladera,
                                LocalDateTime horaSolicitud,
                                MotivoApertura motivoApertura) {

        AperturaHeladera aperturaHeladera = new AperturaHeladera();
        aperturaHeladera.setTarjeta(this.repositorioTarjetaColaborador.buscarPorColaborador(personaHumana).get(0));
        aperturaHeladera.setTarjeta(tarjetaColaborador);
        aperturaHeladera.setHoraSolicitud(horaSolicitud);
        aperturaHeladera.setHeladera(heladera);
        aperturaHeladera.setMotivo(motivoApertura);
        aperturaHeladera.setHoraLimite(horaSolicitud.plusHours(heladera.getHorasParaApertura()));
        aperturaHeladera.setEstado(EstadoApertura.SOLICITADA);
        aperturaHeladeraRepository.guardar(aperturaHeladera);

        this.enviarAperturaHeladera(AperturaHeladeraFactory.toBrokerDTO(aperturaHeladera));
    }

    @Override
    public void enviarAperturaHeladera(AperturaHeladeraBrokerOutputDTO out) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String data = null;
        try {
            data = objectMapper.writeValueAsString(out);
        } catch (JsonProcessingException e) {
            throw new CouldNotConvertToJsonException(e);
        }

        try {
            broker.connect();
        } catch (MqttException e) {
            throw new CouldNotConnectToBrokerException(e);
        }

        try {
            String topic = brokerProperties.getProperty("topic_aperturas") + "/" + out.getIdHeladera();
            broker.publish(topic, data, 2);
        } catch (MqttException e) {
            throw new CouldNotPublishMessageException(e);
        }

        try {
            broker.disconnect();
        } catch (MqttException e) {
            throw new CouldNotDisconnectFromBrokerException(e);
        }

    }

    @Override
    public void recibirRespuestaAperturaHeladera(AperturaHeladeraInputDTO in) {
        Optional<AperturaHeladera> posibleApertura = aperturaHeladeraRepository.buscarPorId(in.getIdApertura());
        if (posibleApertura.isPresent()) {
            AperturaHeladera apertura = posibleApertura.get();
            apertura.setEstado(EstadoApertura.valueOf(in.getEstadoApertura()));
            apertura.setHoraApertura(in.getHoraApertura());
            apertura.getTarjeta().getOwner().agregarPuntos(1.5);
            aperturaHeladeraRepository.guardarAperturaHeladera(apertura);
        } else {
            throw new AperturaNotFoundException("No se encontró la apertura con id: " + in.getIdApertura());
        }
    }
}
