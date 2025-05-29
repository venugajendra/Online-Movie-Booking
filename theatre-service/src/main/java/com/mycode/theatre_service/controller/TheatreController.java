package com.mycode.theatre_service.controller;

import com.mycode.theatre_service.model.Theatre;
import com.mycode.theatre_service.service.TheatreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/theatres")
@Tag(name = "Theatre Management", description = "APIs related to theatre management")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

        @Operation(summary = "Get all theatres")
    @GetMapping
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        log.info("Inside the getAllTheatres method of TheatreController.");
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }

    @Operation(summary = "Get a theatre by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable Long id) {
        log.info("Inside the getTheatreById method of TheatreController.");
        Optional<Theatre> theatre = theatreService.getTheatreById(id);
        if (theatre.isPresent()) {
            return ResponseEntity.ok(theatre.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get theatres by location")
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Theatre>> getTheatresByLocation(@PathVariable String location) {
        log.info("Inside the getTheatresByLocation method of TheatreController.");
        return ResponseEntity.ok(theatreService.getTheatresByLocation(location));
    }

    @Operation(summary = "Create a new theatre")
    @PostMapping
    public ResponseEntity<Theatre> createTheatre(@RequestBody Theatre theatre) {
        log.info("Inside the createTheatre method of TheatreController.");
        Theatre createdTheatre = theatreService.createTheatre(theatre);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTheatre);
    }

    @Operation(summary = "Update an existing theatre")
    @PutMapping("/{id}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable Long id, @RequestBody Theatre theatre) {
        log.info("Inside the updateTheatre method of TheatreController.");
        Theatre updatedTheatre = theatreService.updateTheatre(id, theatre);
        if (updatedTheatre != null) {
            return ResponseEntity.ok(updatedTheatre);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a theatre by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long id) {
        log.info("Inside the deleteTheatre method of TheatreController.");
        theatreService.deleteTheatre(id);
        return ResponseEntity.noContent().build();
    }
}
