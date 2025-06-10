package com.mycode.payment_service.exception;

public enum PaymentErrorCode {
    PAYMENT_FAILED("PAYMENT_001", "Payment transaction failed."),
    INSUFFICIENT_FUNDS("PAYMENT_002", "Insufficient funds for the transaction."),
    INVALID_PAYMENT_METHOD("PAYMENT_003", "Invalid or unsupported payment method."),
    PAYMENT_GATEWAY_ERROR("PAYMENT_004", "Payment gateway returned an error."),
    PAYMENT_NOT_FOUND("PAYMENT_005", "Payment transaction not found."),
    PAYMENT_SERVICE_UNEXPECTED_ERROR("PAYMENT_999", "An unexpected error occurred in the payment service.");

    private final String code;
    private final String defaultMessage;

    PaymentErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}