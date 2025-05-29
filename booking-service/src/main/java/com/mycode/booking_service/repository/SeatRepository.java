package com.mycode.booking_service.repository;

import com.mycode.booking_service.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findAllById(Iterable<Long> ids);

/*    List<Seat> findByShowId(Long showId);
    List<Seat> findByBookingId(Long bookingId);
    List<Seat> findByShowIdAndBookingId(Long showId, Long bookingId);
    List<Seat> findByShowIdAndRowNumberAndSeatNumber(Long showId, String rowNumber, int seatNumber);
    List<Seat> findByShowIdAndRowNumber(Long showId, String rowNumber);*//*
    List<Seat> findByShowIdAndSeatNumber(Long showId, int seatNumber);*/
    List<Seat> findByRowNumberAndSeatNumber(String rowNumber, int seatNumber);

}
