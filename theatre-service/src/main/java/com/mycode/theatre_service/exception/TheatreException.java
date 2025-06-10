package com.mycode.theatre_service.exception;

public abstract class TheatreException extends RuntimeException {

    private final TheatreErrorCode errorCode;

    public TheatreException(String message, TheatreErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public TheatreException(String message, Throwable cause, TheatreErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public TheatreErrorCode getErrorCodeEnum() {
        return errorCode;
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }
}
