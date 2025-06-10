package com.mycode.theatre_service.exception;

public class InvalidTheatreDataException extends TheatreException {
    public InvalidTheatreDataException(String message) {
        super(message, TheatreErrorCode.INVALID_THEATRE_DATA);
    }
}
