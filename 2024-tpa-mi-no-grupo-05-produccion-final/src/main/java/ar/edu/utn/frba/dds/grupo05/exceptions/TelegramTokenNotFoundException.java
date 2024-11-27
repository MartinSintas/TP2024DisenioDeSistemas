package ar.edu.utn.frba.dds.grupo05.exceptions;

public class TelegramTokenNotFoundException extends RuntimeException {
    public TelegramTokenNotFoundException(String message) {
        super(message);
    }
}
