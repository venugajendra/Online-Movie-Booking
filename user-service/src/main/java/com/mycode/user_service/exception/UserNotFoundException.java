package com.mycode.user_service.exception;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String message) {
        super(message, UserErrorCode.USER_NOT_FOUND);
    }
}
