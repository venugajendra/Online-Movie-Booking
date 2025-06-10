package com.mycode.theatre_service.exception;

public class DuplicateScreenNameException extends TheatreException {
    public DuplicateScreenNameException(String message) {
        super(message, TheatreErrorCode.DUPLICATE_SCREEN_NAME);
    }
}
