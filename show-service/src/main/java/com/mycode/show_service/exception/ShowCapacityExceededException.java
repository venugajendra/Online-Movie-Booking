package com.mycode.show_service.exception;

public class ShowCapacityExceededException extends ShowException {
    public ShowCapacityExceededException(String message) {
        super(message, ShowErrorCode.SEATS_UNAVAILABLE);
    }
}