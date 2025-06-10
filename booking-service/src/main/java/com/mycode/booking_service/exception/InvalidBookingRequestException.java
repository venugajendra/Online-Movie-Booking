package com.mycode.booking_service.exception;

public class InvalidBookingRequestException extends BookingException {
    public InvalidBookingRequestException(String message) {
        super(message, BookingErrorCode.INVALID_BOOKING_REQUEST); // Pass enum constant
    }
    public InvalidBookingRequestException(String message, Throwable cause) {
        super(message, cause, BookingErrorCode.INVALID_BOOKING_REQUEST);
    }
}