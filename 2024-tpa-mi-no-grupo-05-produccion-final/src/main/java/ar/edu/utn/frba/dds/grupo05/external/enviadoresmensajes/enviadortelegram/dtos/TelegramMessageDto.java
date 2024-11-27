package ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TelegramMessageDto {
    private String chat_id;
    private String text;
}
