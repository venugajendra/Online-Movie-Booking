package com.mycode.show_service.service;

import com.mycode.show_service.client.MovieClient;
import com.mycode.show_service.client.TheatreClient;
import com.mycode.show_service.dto.MovieDTO;
import com.mycode.show_service.dto.ScreenDTO;
import com.mycode.show_service.dto.ShowWithScreenDTO;
import com.mycode.show_service.dto.TheatreDTO;
import com.mycode.show_service.model.Show;
import com.mycode.show_service.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieClient movieClient;

    @Autowired
    private TheatreClient theatreClient;

    public ShowService(ShowRepository showRepository, MovieClient movieClient, TheatreClient theatreClient) {
        this.showRepository = showRepository;
        this.movieClient = movieClient;
        this.theatreClient = theatreClient;
    }



    public ShowWithScreenDTO getShowDetails(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        ScreenDTO screen = theatreClient.getScreenById(show.getScreenId());

        return new ShowWithScreenDTO(show, screen);
    }

    public Show createShow(Show show) {
        return showRepository.save(show);
    }

 /*   public List<Show> getShowsByMovieAndTownAndDate(Long movieId, String town, LocalDate date) {
        // 1. Validate input parameters
        if (movieId == null || town == null || date == null) {
            throw new IllegalArgumentException("Movie ID, town, and date must be provided.");
        }

        // 2. Fetch the movie
//        Optional<Movie> movieOptional = movieService.getMovieById(movieId);
        MovieDTO movieDTO = movieClient.getMovieById(movieId);

        if (movieDTO == null) {
            throw new IllegalArgumentException("Movie not found.");
        }
        Movie movie = movieOptional.get();

        // 3. Fetch the theatres in the given town
        List<com.mycode.theatre_service.model.Theatre> theatres = theatreService.getTheatresByLocation(town);

        if (theatres.isEmpty()) {
            return List.of();
        }

        // 4. Fetch shows for the movie and date
        List<Show> shows = showRepository.findByMovieAndShowDate(movie, date);

        // 5. Filter shows by theatre.
        List<Show> result = shows.stream()
                .filter(show -> {
                    for (com.mycode.theatre_service.model.Theatre theatre : theatres) {
                        for(Screen screen: theatre.getScreens()){
                            if (show.getScreenId().equals(screen.getId()))
                                return true;
                        }

                    }
                    return false;
                })
                .collect(Collectors.toList());
        return result;
    }

        // 3. Fetch the theatres in the given town
        List<TheatreDTO> theatres = theatreClient.getTheatresByLocation(town); // Assuming you use a TheatreDTO
        if (theatres.isEmpty()) {
            return List.of();
        }

        // 4. Fetch shows by movieId and date
        List<Show> shows = showRepository.findByMovieAndShowDate(movieClient.getMovieById(movieId), date);

        List<Show> shows = showRepository.findByShowDate(date);

        // 5. Filter shows by theatre
        Set<Long> validScreenIds = theatres.stream()
                .flatMap(theatre -> theatre.getScreens().stream())
                .map(ScreenDTO::getId) // Assuming screens are DTOs too
                .collect(Collectors.toSet());

        return shows.stream()
                .filter(show -> validScreenIds.contains(show.getScreenId()))
                .collect(Collectors.toList());

        public List<Show> getShowsByTheatreAndDate (Long theatreId, LocalDate date){
            return showRepository.findByScreen_Theatre_IdAndShowDate(theatreId, date);
        }

        public Optional<Show> getShowById (Long id){
            return showRepository.findById(id);
        }

        public Show updateShow (Long id, Show show){
            Optional<Show> existingShow = showRepository.findById(id);
            if (existingShow.isPresent()) {
                show.setId(id);
                return showRepository.save(show);
            }
            return null;
        }

        public void deleteShow (Long id){
            showRepository.deleteById(id);
        }

    }*/


    public List<Show> getShowsByMovieAndTownAndDate(Long movieId, String town, LocalDate date) {
        // 1. Validate input parameters
        if (movieId == null || town == null || date == null) {
            throw new IllegalArgumentException("Movie ID, town, and date must be provided.");
        }

        // 2. Fetch movie via Feign client (assumes MovieDTO is returned)
        MovieDTO movieDTO = movieClient.getMovieById(movieId);
        if (movieDTO == null) {
            throw new IllegalArgumentException("Movie not found.");
        }

        // 3. Fetch theatres in the town via Theatre Feign client
        List<TheatreDTO> theatres = theatreClient.getTheatresByLocation(town);
        if (theatres == null || theatres.isEmpty()) {
            return List.of(); // No theatres in town
        }

        // 4. Extract all screen IDs from those theatres
        Set<Long> screenIds = new HashSet<>();
        for (TheatreDTO theatre : theatres) {
            if (theatre.getScreens() != null) {
                for (ScreenDTO screen : theatre.getScreens()) {
                    screenIds.add(screen.getId());
                }
            }
        }

        if (screenIds.isEmpty()) {
            return List.of(); // No screens found for those theatres
        }

        // 5. Fetch shows by movie ID and date
        List<Show> allShows = showRepository.findByMovieIdAndShowDate(movieId, date);

        // 6. Filter shows that belong to the screens in the specified town
        List<Show> matchingShows = allShows.stream()
                .filter(show -> screenIds.contains(show.getScreenId()))
                .collect(Collectors.toList());

        return matchingShows;


    }

/*    // 7. Fetch shows by date
    public List<Show> getShowsByTheatreAndDate(Long theatreId, LocalDate date) {
        return showRepository.findByScreen_Theatre_IdAndShowDate(theatreId, date);
    }*/
    public Optional<Show> getShowById (Long id){
        return showRepository.findById(id);
    }
}
