package com.mycode.show_service.exception;

public class ShowNotFoundException extends ShowException {
    public ShowNotFoundException(String message) {
        super(message, ShowErrorCode.SHOW_NOT_FOUND);
    }
}
