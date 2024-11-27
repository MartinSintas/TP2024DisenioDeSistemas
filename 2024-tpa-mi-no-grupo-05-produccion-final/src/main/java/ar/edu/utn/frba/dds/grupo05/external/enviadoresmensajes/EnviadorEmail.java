package ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class EnviadorEmail implements EnviadorMensaje {

    private final Properties mailProperties = new Properties();
    private final Logger logger = LoggerFactory.getLogger(EnviadorEmail.class);;

    public EnviadorEmail() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.Mail.properties")) {
            if (input == null) {
                System.out.println("No se encontró el config.Mail.properties");
                return;
            }
            mailProperties.load(input);
        } catch (IOException e) {
            logger.error("Error al cargar el archivo de configuración de mail: {}", Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void enviarNotificacion(String direccion, String mensaje) {
        enviarNotificacion(direccion, "Notificación", mensaje);
    }

    @Override
    public void enviarNotificacion(String direccion, String asunto, String mensaje) {
        Properties props = new Properties();
        props.put("mail.smtp.host", mailProperties.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", mailProperties.getProperty("mail.smtp.port"));
        props.put("mail.smtp.auth", mailProperties.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", mailProperties.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.socketFactory.class", mailProperties.getProperty("mail.smtp.socketFactory.class"));

        final String username = mailProperties.getProperty("mail.username");
        final String password = mailProperties.getProperty("mail.password");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailProperties.getProperty("mail.from")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(direccion));
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);
            this.logger.info("Correo enviado correctamente a {}", direccion);

        } catch (MessagingException e) {
            this.logger.error("Error al enviar el correo a {}, {}", direccion, Arrays.toString(e.getStackTrace()));
        }
    }
}
