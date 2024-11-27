package ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram;

import ar.edu.utn.frba.dds.grupo05.exceptions.TelegramBotApiCallException;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.EnviadorMensaje;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.dtos.TelegramMessageDto;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.dtos.TelegramResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Properties;

public class EnviadorTelegram implements EnviadorMensaje {

    private static String botToken;
    private static String baseUrl;
    private ApiBotTelegram apiBotTelegram;
    private final Logger logger = LoggerFactory.getLogger(EnviadorTelegram.class);

    public EnviadorTelegram() throws IOException {
        if (botToken == null || baseUrl == null) {
            String path = "config.Telegram.properties";
            Properties appProps = new Properties();
            appProps.load(getClass().getClassLoader().getResourceAsStream(path));

            botToken = appProps.getProperty("botToken");
            baseUrl = appProps.getProperty("baseURL");
        }

        this.apiBotTelegram = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
                .create(ApiBotTelegram.class);
    }

    @Override
    public void enviarNotificacion(String chatId, String mensaje) {
        TelegramMessageDto message = new TelegramMessageDto(chatId, mensaje);
        Call<TelegramResponseDTO> request = this.apiBotTelegram.enviarMensaje(botToken, message);

        try {
            Response<TelegramResponseDTO> responseDTO = request.execute();
            if (responseDTO.isSuccessful() && responseDTO.body().isOk()) {
                logger.info("Mensaje enviado correctamente a {}", chatId);
            } else {
                logger.error("No se pudo enviar el mensaje a {}", chatId);
            }
        } catch (IOException e) {
            logger.error("Error al enviar el mensaje de telegram: {}", e.getMessage());
            throw new TelegramBotApiCallException("Error al enviar el mensaje de telegram: " + e.getMessage());
        }

    }

    @Override
    public void enviarNotificacion(String chatId, String asunto, String mensaje) {
        String fullMessage = asunto +"\n\n" + mensaje;
        this.enviarNotificacion(chatId, fullMessage);
    }
    

}
