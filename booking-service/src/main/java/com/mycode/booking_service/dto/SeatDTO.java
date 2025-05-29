package com.mycode.booking_service.dto;

import lombok.Data;

@Data
public class SeatDTO {

    private Long id;
    private String rowNumber;
    private int seatNumber;
    private Long showId;
    private Long bookingId;
}
