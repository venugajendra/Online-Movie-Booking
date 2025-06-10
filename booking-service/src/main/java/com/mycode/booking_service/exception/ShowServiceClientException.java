package com.mycode.booking_service.exception;

public class ShowServiceClientException extends BookingException {
    public ShowServiceClientException(String message, Throwable cause) {
        super(message, cause, BookingErrorCode.EXTERNAL_SHOW_SERVICE_ERROR);
    }

    public ShowServiceClientException(Throwable cause) {
        super(cause.getMessage(), cause, BookingErrorCode.EXTERNAL_SHOW_SERVICE_ERROR);
    }
}
