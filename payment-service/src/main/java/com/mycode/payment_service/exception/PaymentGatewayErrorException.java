package com.mycode.payment_service.exception;

public class PaymentGatewayErrorException extends PaymentException {
    public PaymentGatewayErrorException(String message) {
        super(message, PaymentErrorCode.PAYMENT_GATEWAY_ERROR);
    }
    public PaymentGatewayErrorException(String message, Throwable cause) {
        super(message, cause, PaymentErrorCode.PAYMENT_GATEWAY_ERROR);
    }
}
