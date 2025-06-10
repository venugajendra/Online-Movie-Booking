package com.mycode.show_service.exception;

public class ShowPastDateException extends ShowException {
    public ShowPastDateException(String message) {
        super(message, ShowErrorCode.SHOW_PAST_DATE);
    }
}
