package com.mycode.booking_service.controller;

import com.mycode.booking_service.dto.SeatDTO;
import com.mycode.booking_service.model.Seat;
import com.mycode.booking_service.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/bulk")
    public ResponseEntity<List<Seat>> createBulkSeats(@RequestBody List<SeatDTO> seatDTOs) {
        log.info("Inside the createBulkSeats method of SeatController.");
        List<Seat> createdSeats = seatService.createSeats(seatDTOs);
        return new ResponseEntity<>(createdSeats, HttpStatus.CREATED);
    }


    @PostMapping("/single")
    public ResponseEntity<Seat> createSeat(@RequestBody SeatDTO seatDTO) {
        log.info("Inside the createSeat method of SeatController.");
        Seat created = seatService.createSeat(seatDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id) {
        log.info("Inside the getSeatById method of SeatController.");
        Seat seat = seatService.getSeatById(id);
        if (seat != null) {
            return ResponseEntity.ok(seat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/by-ids")
    public ResponseEntity<List<Seat>> getSeatsByIds(@RequestBody List<Long> seatIds) {
        log.info("Inside the getSeatByIds method of SeatController.");
        List<Seat> seats = seatService.getSeatsByIds(seatIds);
        return ResponseEntity.ok(seats);
    }

/*    @GetMapping("/show/{showId}")
    public ResponseEntity<List<Seat>> getSeatsByShow(@PathVariable Long showId) {
        return ResponseEntity.ok(seatService.getSeatsByShowId(showId));
    }*/



}
