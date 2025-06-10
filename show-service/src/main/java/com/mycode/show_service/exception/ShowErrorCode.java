package com.mycode.show_service.exception;

public enum ShowErrorCode {
    SHOW_NOT_FOUND("SHOW_001", "Show with the specified ID was not found."),
    SHOW_PAST_DATE("SHOW_002", "Show date cannot be in the past."),
    SHOW_INVALID_CAPACITY("SHOW_003", "Invalid show capacity provided."),
    SEATS_UNAVAILABLE("SHOW_004", "Not enough seats available for the show."),
    SHOW_SERVICE_UNEXPECTED_ERROR("SHOW_999", "An unexpected error occurred in the show service.");

    private final String code;
    private final String defaultMessage;

    ShowErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
