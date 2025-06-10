package com.mycode.booking_service.exception;

public class PastShowBookingException extends BookingException {
    public PastShowBookingException(String message) {
        super(message, BookingErrorCode.PAST_SHOW_BOOKING);
    }
    public PastShowBookingException() {
        super(BookingErrorCode.PAST_SHOW_BOOKING);
    }
}
