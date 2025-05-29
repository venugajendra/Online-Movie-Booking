package com.mycode.movie_service.service;

import com.mycode.movie_service.model.Movie;
import com.mycode.movie_service.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> getAllMovies() {
        log.info("Inside the getAllMovies method of MovieService.");
        return movieRepository.findAll();
    }
    public Optional<Movie> getMovieById(Long id) {
        log.info("Inside the getMovieById method of MovieService.");
        return movieRepository.findById(id);
    }
    public Movie createMovie(Movie movie) {
        log.info("Inside the createMovie method of MovieService.");
        return movieRepository.save(movie);
    }
    public Movie updateMovie(Long id, Movie movie) {
        log.info("Inside the updateMovie method of MovieService.");
        Optional<Movie> existingMovie = movieRepository.findById(id);
        if (existingMovie.isPresent()) {
            movie.setId(id); // Ensure the ID is set
            return movieRepository.save(movie);
        }
        return null; // Or throw an exception
    }

    public void deleteMovie(Long id) {
        log.info("Inside the deleteMovie method of MovieService.");
        movieRepository.deleteById(id);
    }
}