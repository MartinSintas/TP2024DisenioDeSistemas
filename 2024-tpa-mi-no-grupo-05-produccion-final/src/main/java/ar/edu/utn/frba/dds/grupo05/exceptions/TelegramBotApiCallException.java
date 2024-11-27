package ar.edu.utn.frba.dds.grupo05.exceptions;

public class TelegramBotApiCallException extends RuntimeException {
    public TelegramBotApiCallException(String errorAlEnviarElMensaje) {
        super(errorAlEnviarElMensaje);
    }
}
