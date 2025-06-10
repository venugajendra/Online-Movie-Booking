package com.mycode.payment_service.exception;

public class PaymentFailedException extends PaymentException {
    public PaymentFailedException(String message) {
        super(message, PaymentErrorCode.PAYMENT_FAILED);
    }
    public PaymentFailedException(String message, Throwable cause) {
        super(message, cause, PaymentErrorCode.PAYMENT_FAILED);
    }
}