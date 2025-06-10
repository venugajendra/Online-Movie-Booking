package com.mycode.show_service.exception;

public abstract class ShowException extends RuntimeException {
    private final ShowErrorCode errorCode;

    public ShowException(String message, ShowErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ShowException(String message, Throwable cause, ShowErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ShowErrorCode getErrorCodeEnum() {
        return errorCode;
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }
}
