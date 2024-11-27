package ar.edu.utn.frba.dds.grupo05.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class CouldNotConvertToJsonException extends RuntimeException {
    public CouldNotConvertToJsonException(JsonProcessingException e) {
        super(e);
    }
}
