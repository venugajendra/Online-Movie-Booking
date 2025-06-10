package com.mycode.user_service.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum UserErrorCode {
    USER_NOT_FOUND("USER_001", "User with the specified ID was not found."),
    DUPLICATE_USER_EMAIL("USER_002", "A user with this email already exists."),
    INVALID_USER_DATA("USER_003", "Invalid user data provided."),
    USER_SERVICE_UNEXPECTED_ERROR("USER_999", "An unexpected error occurred in the user service.");

    private String code;
    private String defaultMessage;

    public String getCode() {
        return code;
    }

}
