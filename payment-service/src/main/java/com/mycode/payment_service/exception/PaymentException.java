package com.mycode.payment_service.exception;

public abstract class PaymentException extends RuntimeException {
    private final PaymentErrorCode errorCode;

    public PaymentException(String message, PaymentErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public PaymentException(String message, Throwable cause, PaymentErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public PaymentErrorCode getErrorCodeEnum() {
        return errorCode;
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }
}
