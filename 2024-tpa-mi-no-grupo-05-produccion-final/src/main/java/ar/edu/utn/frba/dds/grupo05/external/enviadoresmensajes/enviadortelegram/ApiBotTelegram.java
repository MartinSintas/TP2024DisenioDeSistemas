package ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram;

import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.dtos.TelegramMessageDto;
import ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.dtos.TelegramResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiBotTelegram {
    @POST("/bot{token}/sendMessage")
    Call<TelegramResponseDTO> enviarMensaje(@Path("token") String token, @Body TelegramMessageDto message);
}
