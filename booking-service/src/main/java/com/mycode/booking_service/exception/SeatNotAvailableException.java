package com.mycode.booking_service.exception;

public class SeatNotAvailableException extends BookingException {
    public SeatNotAvailableException(String message) {
        super(message, BookingErrorCode.SEAT_NOT_AVAILABLE);
    }
    public SeatNotAvailableException() {
        super(BookingErrorCode.SEAT_NOT_AVAILABLE);
    }
}
