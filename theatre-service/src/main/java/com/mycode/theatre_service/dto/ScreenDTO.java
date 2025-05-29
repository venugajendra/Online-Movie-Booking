package com.mycode.theatre_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreenDTO {

    private Long id;
    private String name;
    private Long theatreId;
    private int totalSeats;
}
