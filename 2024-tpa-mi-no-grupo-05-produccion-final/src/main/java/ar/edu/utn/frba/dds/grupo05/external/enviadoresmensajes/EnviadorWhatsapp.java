package ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class EnviadorWhatsapp implements  EnviadorMensaje {

    private String ACOUNT_SID;
    private String AUTH_TOKEN;
    private String FROM_PHONE;
    private final Logger logger = LoggerFactory.getLogger(EnviadorWhatsapp.class);

    public EnviadorWhatsapp() {
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("config.WhatsApp.properties")){
            Properties prop = new Properties();
            if (input == null){
                logger.error("No se pudo encontrar la config.WhatsApp.properties");
                return;
            }
            prop.load(input);
            this.ACOUNT_SID = prop.getProperty("twilio.account_sid");
            this.AUTH_TOKEN = prop.getProperty("twilio.auth_token");
            this.FROM_PHONE = prop.getProperty("twilio.from_phone");

            Twilio.init(ACOUNT_SID, AUTH_TOKEN);
        } catch (IOException ex){
            logger
            .error("Error al cargar el archivo de configuración de WhatsApp: {}", Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void enviarNotificacion(String direccion, String mensaje){
        try {
            Message message = Message.creator(
                    new PhoneNumber("Whatsapp:" + direccion),
                    new PhoneNumber("whatsapp:" + FROM_PHONE),
                    mensaje
            ).create();

            logger.info("Mensaje enviado con SID: {}", message.getSid());
        } catch (ApiException e){
            logger.error("Error al enviar el mensaje: {}", e.getMessage());
        }
    }

    @Override
    public void enviarNotificacion(String direccion, String asunto, String mensaje) {
        String mensajeCompleto = asunto + "\n\n" + mensaje;
        enviarNotificacion(direccion, mensajeCompleto);
    }
}
