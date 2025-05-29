package com.mycode.theatre_service.service;

import com.mycode.theatre_service.model.Theatre;
import com.mycode.theatre_service.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public Optional<Theatre> getTheatreById(Long id) {
        return theatreRepository.findById(id);
    }

    public List<Theatre> getTheatresByLocation(String location) {
        return theatreRepository.findByLocation(location);
    }

    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public Theatre updateTheatre(Long id, Theatre theatre) {
        Optional<Theatre> existingTheatre = theatreRepository.findById(id);
        if (existingTheatre.isPresent()) {
            theatre.setId(id);
            return theatreRepository.save(theatre);
        }
        return null;
    }

    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }
}
