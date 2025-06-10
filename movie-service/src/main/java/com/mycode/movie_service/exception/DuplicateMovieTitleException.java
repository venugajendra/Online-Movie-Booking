package com.mycode.movie_service.exception;

public class DuplicateMovieTitleException extends MovieException {
    public DuplicateMovieTitleException(String message) {
        super(message, MovieErrorCode.DUPLICATE_MOVIE_TITLE);
    }
}
