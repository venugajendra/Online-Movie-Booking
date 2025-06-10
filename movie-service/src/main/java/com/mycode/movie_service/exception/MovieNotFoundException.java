package com.mycode.movie_service.exception;

public class MovieNotFoundException extends MovieException {
    public MovieNotFoundException(String message) {
        super(message, MovieErrorCode.MOVIE_NOT_FOUND);
    }
}