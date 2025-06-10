package com.mycode.movie_service.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum MovieErrorCode {
    MOVIE_NOT_FOUND("MOVIE_001", "Movie with the specified ID was not found."),
    DUPLICATE_MOVIE_TITLE("MOVIE_002", "A movie with this title already exists."),
    INVALID_MOVIE_DATA("MOVIE_003", "Invalid movie data provided."),
    MOVIE_SERVICE_UNEXPECTED_ERROR("MOVIE_999", "An unexpected error occurred in the movie service.");

    private String code;
    private String defaultMessage;

    MovieErrorCode(String code, String defaultMessage) {
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
