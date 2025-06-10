package com.mycode.payment_service.exception;

public class InsufficientFundsException extends PaymentException {
    public InsufficientFundsException(String message) {
        super(message, PaymentErrorCode.INSUFFICIENT_FUNDS);
    }
}