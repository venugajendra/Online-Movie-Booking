package com.mycode.theatre_service.exception;

public class ScreenNotFoundException extends TheatreException {
    public ScreenNotFoundException(String message) {
        super(message, TheatreErrorCode.SCREEN_NOT_FOUND);
    }
}
