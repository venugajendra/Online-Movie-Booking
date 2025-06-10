package com.mycode.booking_service.exception;

public class SeatServiceClientException extends BookingException{
    public SeatServiceClientException(String message, Throwable cause) {
        super(message, cause, BookingErrorCode.EXTERNAL_SEAT_SERVICE_ERROR);
    }

    public SeatServiceClientException(Throwable cause) {
        super(cause.getMessage(), cause, BookingErrorCode.EXTERNAL_SEAT_SERVICE_ERROR);
    }
}
