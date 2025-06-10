package com.mycode.theatre_service.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum TheatreErrorCode {
    THEATRE_NOT_FOUND("THEATRE_001", "Theatre with the specified ID was not found."),
    SCREEN_NOT_FOUND("THEATRE_002", "Screen with the specified ID was not found."),
    INVALID_SCREEN_CAPACITY("THEATRE_003", "Invalid screen capacity provided."),
    DUPLICATE_SCREEN_NAME("THEATRE_004", "A screen with this name already exists in the theatre."),
    INVALID_THEATRE_DATA("THEATRE_005", "Invalid theatre data provided."),
    THEATRE_SERVICE_UNEXPECTED_ERROR("THEATRE_999", "An unexpected error occurred in the theatre service.");

    private String code;
    private String defaultMessage;

    TheatreErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

/*    public String getDefaultMessage() {
        return defaultMessage;
    }*/
}
