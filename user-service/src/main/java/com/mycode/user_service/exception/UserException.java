package com.mycode.user_service.exception;


public abstract class UserException extends RuntimeException {

    private final UserErrorCode errorCode;

    public UserException(String message, UserErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public UserException(String message, Throwable cause, UserErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public UserErrorCode getErrorCodeEnum() {
        return errorCode;
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }
}
