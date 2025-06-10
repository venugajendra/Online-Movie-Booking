package com.mycode.booking_service.exception;

public class UserServiceClientException extends BookingException {
    public UserServiceClientException(String message, Throwable cause) {
        super(message, cause, BookingErrorCode.EXTERNAL_USER_SERVICE_ERROR);
    }
    public UserServiceClientException(Throwable cause) {
        super(cause.getMessage(), cause, BookingErrorCode.EXTERNAL_USER_SERVICE_ERROR);
//        super(cause, BookingErrorCode.EXTERNAL_USER_SERVICE_ERROR);
    }
}
