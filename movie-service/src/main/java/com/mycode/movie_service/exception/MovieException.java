package com.mycode.movie_service.exception;

public abstract class MovieException extends RuntimeException {

    private final MovieErrorCode errorCode;

    public MovieException(String message, MovieErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public MovieException(String message, Throwable cause, MovieErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public MovieErrorCode getErrorCodeEnum() {
        return errorCode;
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }
}
