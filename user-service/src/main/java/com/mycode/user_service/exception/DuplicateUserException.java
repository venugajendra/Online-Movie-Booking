package com.mycode.user_service.exception;

public class DuplicateUserException extends UserException {
    public DuplicateUserException(String message) {
        super(message, UserErrorCode.DUPLICATE_USER_EMAIL);
    }
}