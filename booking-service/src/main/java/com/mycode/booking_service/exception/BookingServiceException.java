package com.mycode.booking_service.exception;

public class BookingServiceException extends BookingException {
    public BookingServiceException(String message, Throwable cause) {
        super(message, cause, BookingErrorCode.INTERNAL_SERVER_ERROR); // Use generic internal error
    }
}
