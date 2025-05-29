package com.mycode.booking_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rowNumber;
    private int seatNumber;
    private Long showId;
    private Long bookingId; // Nullable, set after booking



}


    /*    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = true) // Allow null for now, set after booking
    private Booking booking;*/