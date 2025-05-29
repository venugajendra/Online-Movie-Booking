package com.mycode.theatre_service.service;

import com.mycode.theatre_service.dto.ScreenDTO;
import com.mycode.theatre_service.model.Screen;
import com.mycode.theatre_service.model.Theatre;
import com.mycode.theatre_service.repository.ScreenRepository;
import com.mycode.theatre_service.repository.TheatreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public ScreenDTO createScreen(ScreenDTO dto) {
        log.info("Inside the createScreen method of ScreenService.");
        Theatre theatre = theatreRepository.findById(dto.getTheatreId())
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        Screen screen = new Screen();
        screen.setName(dto.getName());
        screen.setTheatre(theatre);
        screen.setTotalSeats(dto.getTotalSeats());

        // Save first — let JPA generate the ID
//      screen = screenRepository.save(screen);
        screenRepository.save(screen);

        // Now set the ID back in DTO to return
        return new ScreenDTO(screen.getId(), screen.getName(), theatre.getId(), screen.getTotalSeats());
    }

    public List<ScreenDTO> getAllScreens() {
        log.info("Inside the getAllScreens method of ScreenService.");
        return screenRepository.findAll().stream()
                .map(s -> new ScreenDTO(s.getId(), s.getName(), s.getTheatre().getId(), s.getTotalSeats()))
                .collect(Collectors.toList());
    }
}
