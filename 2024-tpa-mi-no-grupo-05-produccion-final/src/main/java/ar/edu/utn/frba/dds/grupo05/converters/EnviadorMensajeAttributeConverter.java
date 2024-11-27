package ar.edu.utn.frba.dds.grupo05.converters;

import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorEmail;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorMensaje;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorTelefono;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorWhatsapp;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.EnviadorTelegram;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class EnviadorMensajeAttributeConverter implements AttributeConverter<EnviadorMensaje, String> {


    @Override
    public String convertToDatabaseColumn(EnviadorMensaje enviadorMensaje) {
        if (enviadorMensaje == null) {
            return null;
        }

        String descripcionEnviadorMensaje = "" ;

        if (enviadorMensaje instanceof EnviadorWhatsapp) {
            descripcionEnviadorMensaje = "WHATSAPP";
        }
        else if (enviadorMensaje instanceof EnviadorEmail){
            descripcionEnviadorMensaje = "EMAIL";
        }
        else if (enviadorMensaje instanceof EnviadorTelefono) {
            descripcionEnviadorMensaje = "SMS";
        }
        else if (enviadorMensaje instanceof EnviadorTelegram){
            descripcionEnviadorMensaje = "TELEGRAM";
        }
        return descripcionEnviadorMensaje;
    }

    @Override
    public EnviadorMensaje convertToEntityAttribute(String s) {
        if(s == null){
        return null;
        }
        EnviadorMensaje enviadorMensaje = null;
        switch (s) {
            case "WHATSAPP": enviadorMensaje = new EnviadorWhatsapp(); break;
            case "EMAIL": enviadorMensaje = new EnviadorEmail(); break;
            case "SMS": enviadorMensaje= new EnviadorTelefono(); break;
            case "TELEGRAM":
                try {
                    enviadorMensaje= new EnviadorTelegram();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
        return enviadorMensaje;
    }
}
