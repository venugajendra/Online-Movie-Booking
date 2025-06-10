package com.mycode.booking_service.exception;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base class for all booking-related exceptions.
 * This class extends RuntimeException and provides constructors to set error messages and error codes.
 */
@AllArgsConstructor
@NoArgsConstructor
public abstract class BookingException extends RuntimeException {
    @Autowired
    private BookingErrorCode bookingErrorCode; // Change type to ErrorCode enum

    // Constructor with message and ErrorCode enum
    public BookingException(String message, BookingErrorCode bookingErrorCode) {
        super(message);
        this.bookingErrorCode = bookingErrorCode;
    }

    // Constructor with message, cause, and ErrorCode enum
    public BookingException(String message, Throwable cause, BookingErrorCode bookingErrorCode) {
        super(message, cause);
        this.bookingErrorCode = bookingErrorCode;
    }

    public BookingErrorCode getErrorCodeEnum() { // Renamed getter for clarity
        return bookingErrorCode;
    }

    // You might also want a direct getter for the string code if needed
    public String getBookingErrorCode() {
        return bookingErrorCode.getCode();
    }
}
