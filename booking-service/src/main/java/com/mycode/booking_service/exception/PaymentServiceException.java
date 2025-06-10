package com.mycode.booking_service.exception;

public class PaymentServiceException extends BookingException {
    public PaymentServiceException(String message, Throwable cause) {
        super(message, cause, BookingErrorCode.EXTERNAL_PAYMENT_SERVICE_ERROR);
    }
    public PaymentServiceException(String message) {
        super(message, BookingErrorCode.EXTERNAL_PAYMENT_SERVICE_ERROR);
    }
}
