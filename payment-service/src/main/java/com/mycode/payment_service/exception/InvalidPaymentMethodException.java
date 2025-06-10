package com.mycode.payment_service.exception;

public class InvalidPaymentMethodException extends PaymentException {
    public InvalidPaymentMethodException(String message) {
        super(message, PaymentErrorCode.INVALID_PAYMENT_METHOD);
    }
}
