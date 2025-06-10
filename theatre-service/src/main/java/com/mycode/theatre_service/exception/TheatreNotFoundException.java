package com.mycode.theatre_service.exception;

public class TheatreNotFoundException extends TheatreException {
    public TheatreNotFoundException(String message) {
        super(message, TheatreErrorCode.THEATRE_NOT_FOUND);
    }
}
