package com.mycode.booking_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long showId;

    @ElementCollection             // Store list of Seat IDs
    private List<Long> seatIds;

    private LocalDateTime bookingTime;

    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}



