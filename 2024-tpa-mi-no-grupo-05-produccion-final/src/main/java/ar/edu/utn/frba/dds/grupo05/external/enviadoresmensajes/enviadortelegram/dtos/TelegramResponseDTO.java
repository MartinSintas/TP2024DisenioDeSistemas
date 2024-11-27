package ar.edu.utn.frba.dds.grupo05.external.enviadoresmensajes.enviadortelegram.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramResponseDTO {
    private boolean ok;
    private ResultDTO result;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResultDTO {
        private int message_id;
        private FromDTO from;
        private ChatDTO chat;
        private long date;
        private String text;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FromDTO {
        private long id;
        private boolean is_bot;
        private String first_name;
        private String username;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatDTO {
        private long id;
        private String first_name;
        private String last_name;
        private String username;
        private String type;
    }
}