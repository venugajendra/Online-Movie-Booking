package com.mycode.booking_service.exception;

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
public class GlobalExceptionHandler {

    public record ErrorResponse(
            String timestamp,
            int status,
            String error,
            String message,
            String errorCode, // This is the string code
            String path
    ) {}

    @ExceptionHandler(BookingException.class)
    public ResponseEntity<ErrorResponse> handleBookingException(BookingException ex) {
        log.error("BookingException occurred: {}", ex.getMessage(), ex);
        HttpStatus status;
        // Determine HTTP status based on the specific exception type or the error code enum itself
        if (ex.getErrorCodeEnum() == BookingErrorCode.INVALID_BOOKING_REQUEST ||
                ex.getErrorCodeEnum() == BookingErrorCode.PAST_SHOW_BOOKING ||
                ex.getErrorCodeEnum() == BookingErrorCode.SEAT_NOT_AVAILABLE ||
                ex.getErrorCodeEnum() == BookingErrorCode.SHOW_UNAVAILABLE) {
            status = HttpStatus.BAD_REQUEST; // 400
        } else if (ex.getErrorCodeEnum().name().startsWith("EXTERNAL_")) { // Check enum name for external service errors
            status = HttpStatus.SERVICE_UNAVAILABLE; // 503
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR; // 500 for generic booking service errors or others
        }

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                ex.getBookingErrorCode(), // Use the getter that returns the String code
                "N/A"
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    // ... (other handlers like MethodArgumentNotValidException, RuntimeException, Exception)
    // For MethodArgumentNotValidException, explicitly set ErrorCode.VALIDATION_FAILED
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        String errorMessage = errors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));

        log.error("Validation error in Booking Service: {}", errorMessage);

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation Failed: " + errorMessage,
                BookingErrorCode.VALIDATION_FAILED.getCode(), // Get code from enum
                "N/A"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // For generic RuntimeException, you'd use the fallback error code
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        log.error("An unexpected RuntimeException occurred: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now().toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred. Please try again later.",
                BookingErrorCode.INTERNAL_SERVER_ERROR.getCode(), // Use generic internal error code
                "N/A"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
