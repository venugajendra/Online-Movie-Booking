package com.mycode.show_service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ShowGlobalExceptionHandler {

    public record ErrorResponse(
            String timestamp,
            int status,
            String error,
            String message,
            String errorCode,
            String path
    ) {}

    @ExceptionHandler(ShowException.class)
    public ResponseEntity<ErrorResponse> handleShowException(ShowException ex) {
        log.error("ShowException occurred: {}", ex.getMessage(), ex);
        HttpStatus status;
        if (ex instanceof ShowNotFoundException) {
            status = HttpStatus.NOT_FOUND; // 404
        } else if (ex instanceof ShowPastDateException || ex instanceof ShowCapacityExceededException) {
            status = HttpStatus.BAD_REQUEST; // 400
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        }

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                ex.getErrorCode(),
                "N/A"
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        String errorMessage = errors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));

        log.error("Validation error in Show Service: {}", errorMessage);

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation Failed: " + errorMessage,
                ShowErrorCode.SHOW_SERVICE_UNEXPECTED_ERROR.getCode(), // Consider adding a specific Show validation error code
                "N/A"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error("An unexpected general Exception occurred in Show Service: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred. Please contact support.",
                ShowErrorCode.SHOW_SERVICE_UNEXPECTED_ERROR.getCode(),
                "N/A"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
