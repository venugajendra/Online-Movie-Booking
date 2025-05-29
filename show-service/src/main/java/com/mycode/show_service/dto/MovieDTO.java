package com.mycode.show_service.dto;

import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.List;

@Data
public class MovieDTO {

    private Long id;
    private String title;
    private String description;
    private String genre;
    private String language;
    private String actors;
    private String director;
    private int durationMinutes;

    @ElementCollection
    private List<String> imageUrls;

}
