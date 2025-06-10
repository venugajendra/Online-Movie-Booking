package com.mycode.booking_service.exception;

public class ShowUnavailableException extends BookingException {
    public ShowUnavailableException(String message) {
        super(message, BookingErrorCode.SHOW_UNAVAILABLE); // Pass enum constant
    }
    public ShowUnavailableException(String message, Throwable cause) {
        super(message, cause, BookingErrorCode.SHOW_UNAVAILABLE);
    }
}
