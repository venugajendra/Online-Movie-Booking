package com.mycode.theatre_service.controller;

import com.mycode.theatre_service.dto.ScreenDTO;
import com.mycode.theatre_service.service.ScreenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/screens")
@Tag(name = "Screen Management", description = "APIs related to screen management")
public class ScreenController {

    @Autowired
    private ScreenService screenService;
    @Operation(summary = "Create a new screen")
    @PostMapping
    public ResponseEntity<ScreenDTO> createScreen(@RequestBody ScreenDTO screenDTO) {
        log.info("Inside the createScreen method of ScreenController.");
        ScreenDTO created = screenService.createScreen(screenDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @Operation(summary = "Gat all screens")
    @GetMapping
    public ResponseEntity<List<ScreenDTO>> getAllScreens() {
        log.info("Inside the getAllScreens method of ScreenController.");
        return ResponseEntity.ok(screenService.getAllScreens());
    }
}
