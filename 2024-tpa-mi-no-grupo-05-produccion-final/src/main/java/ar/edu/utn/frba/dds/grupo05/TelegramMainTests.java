package ar.edu.utn.frba.dds.grupo05;

import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.EnviadorTelegram;

import java.io.IOException;

public class TelegramMainTests {
    public static void main(String[] args) throws IOException {
        String chatId = "964610424"; // esto lo hardcodeo porque supongo que el id del telegram del colaborador lo vamos a sacar de algún lado
        EnviadorTelegram sender = new EnviadorTelegram();

        sender.enviarNotificacion(chatId, "Prueba de Asunto", "El salto del papu");
        System.out.println("Mensaje enviado correctamente");
    }
}
