package com.mycode.movie_service.exception;

public class InvalidMovieDataException extends MovieException {
    public InvalidMovieDataException(String message) {
        super(message, MovieErrorCode.INVALID_MOVIE_DATA);
    }
}
