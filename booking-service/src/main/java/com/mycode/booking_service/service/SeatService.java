package com.mycode.booking_service.service;

import com.mycode.booking_service.dto.SeatDTO;
import com.mycode.booking_service.model.Seat;
import com.mycode.booking_service.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    // Create multiple seats
    public List<Seat> createSeats(List<SeatDTO> seatDTOs) {
        List<Seat> seats = seatDTOs.stream().map(dto -> {
            Seat seat = new Seat();
            seat.setRowNumber(dto.getRowNumber());
            seat.setSeatNumber(dto.getSeatNumber());
            seat.setShowId(dto.getShowId());
            seat.setBookingId(null); // initially unbooked
            return seat;
        }).collect(Collectors.toList());

        return seatRepository.saveAll(seats);
    }

    // Get a list of seats by IDs
    public List<Seat> getSeatsByIds(List<Long> seatIds) {
        return seatRepository.findAllById(seatIds);
    }

    // Create a single seat from SeatDTO
    public Seat createSeat(SeatDTO seatDTO) {
        Seat seat = new Seat();
        seat.setRowNumber(seatDTO.getRowNumber());
        seat.setSeatNumber(seatDTO.getSeatNumber());
        seat.setShowId(seatDTO.getShowId());
        seat.setBookingId(null);
        return seatRepository.save(seat);
    }



    // Get a single seat by ID
    public Seat getSeatById(Long seatId) {
        return seatRepository.findById(seatId).orElse(null);
    }

    // More methods: find by showId, mark as booked, etc.
}
