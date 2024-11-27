package ar.edu.utn.frba.dds.grupo05.factories;

import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorEmail;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorMensaje;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorTelefono;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorWhatsapp;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.EnviadorTelegram;

import java.io.IOException;

public class EnviadorMensajeFactory {

  public static EnviadorMensaje crearEnviador(String tipoContacto) {
    switch (tipoContacto.toUpperCase()) {
      case "SMS":
        return new EnviadorTelefono();
      case "EMAIL":
        return new EnviadorEmail();
      case "WHATSAPP":
        return new EnviadorWhatsapp();
      case "TELEGRAM":
        try {
          return new EnviadorTelegram();
        } catch (IOException e) {
          throw new RuntimeException("Error al crear EnviadorTelegram: " + e.getMessage());
        }
      default:
        throw new IllegalArgumentException("Tipo de contacto no soportado: " + tipoContacto);
    }
  }
}

