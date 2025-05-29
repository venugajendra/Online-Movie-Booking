package com.mycode.booking_service.controller;

import com.mycode.booking_service.model.Booking;
import com.mycode.booking_service.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bookings")
@Tag(name = "Booking", description = "APIs related to movie ticket booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Operation(summary = "Book movie tickets")
    @PostMapping
    public ResponseEntity<Booking> bookTickets(
            @RequestParam Long userId,
            @RequestParam Long showId,
            @RequestParam List<Long> seatIds) {
        log.info("Inside the bookTickets method of BookingController.");
        try{
            Booking booking = bookingService.bookTickets(userId, showId, seatIds);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        }
        catch (Exception e) {
            e.printStackTrace(); // Optional: logs to console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Optionally return a structured error
        }

    }
    @Operation(summary = "Get a booking by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        log.info("Inside the getBookingById method of BookingController.");
        Booking booking = bookingService.getBookingById(id);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        }
        return ResponseEntity.notFound().build();
    }
}
