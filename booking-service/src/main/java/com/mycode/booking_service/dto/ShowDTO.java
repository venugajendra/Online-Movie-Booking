package com.mycode.booking_service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowDTO {

    private Long id;
    private LocalDate showDate;
    private LocalTime showTime;
    private double ticketPrice;
    private Long movieId;
    private Long screenId;
}
