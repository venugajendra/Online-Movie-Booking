package com.mycode.booking_service.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum BookingErrorCode {

    // Business Logic Errors
    INVALID_BOOKING_REQUEST("BOOKING_001", "Invalid booking request provided."),
    SHOW_UNAVAILABLE("BOOKING_002", "The requested show is not available or does not exist."),
    SEAT_NOT_AVAILABLE("BOOKING_003", "One or more selected seats are not available."),
    PAST_SHOW_BOOKING("BOOKING_004", "Cannot book tickets for a past show."),

    // External Service Communication Errors
    EXTERNAL_USER_SERVICE_ERROR("EXTERNAL_USER_001", "Failed to retrieve user data from external service."),
    EXTERNAL_SHOW_SERVICE_ERROR("EXTERNAL_SHOW_001", "Failed to retrieve show data from external service."),
    EXTERNAL_SEAT_SERVICE_ERROR("EXTERNAL_SEAT_001", "Failed to retrieve seat data from external service."),
    EXTERNAL_PAYMENT_SERVICE_ERROR("EXTERNAL_PAYMENT_001", "Payment processing failed due to external service issue."),

    // Validation Errors
    VALIDATION_FAILED("VALIDATION_001", "Input validation failed."),

    // Generic Internal Errors
    INTERNAL_SERVER_ERROR("GENERIC_ERROR_999", "An unexpected internal server error occurred.");


    private String code;
    private String defaultMessage;


    public String getCode() {
        return code;
    }
}