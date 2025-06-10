package com.mycode.payment_service.exception;

public class PaymentNotFoundException extends PaymentException {
    public PaymentNotFoundException(String message) {
        super(message, PaymentErrorCode.PAYMENT_NOT_FOUND);
    }
}
