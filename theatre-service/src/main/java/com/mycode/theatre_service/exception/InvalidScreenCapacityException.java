package com.mycode.theatre_service.exception;

public class InvalidScreenCapacityException extends TheatreException {
    public InvalidScreenCapacityException(String message) {
        super(message, TheatreErrorCode.INVALID_SCREEN_CAPACITY);
    }
}
