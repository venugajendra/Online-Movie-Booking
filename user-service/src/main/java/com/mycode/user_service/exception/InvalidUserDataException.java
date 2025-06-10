package com.mycode.user_service.exception;

public class InvalidUserDataException extends UserException {
    public InvalidUserDataException(String message) {
        super(message, UserErrorCode.INVALID_USER_DATA);
    }
}
