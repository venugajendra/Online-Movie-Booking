package com.mycode.show_service.controller;

import com.mycode.show_service.model.Show;
import com.mycode.show_service.service.ShowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shows")
@Tag(name = "Show Management", description = "APIs related to show management")
public class ShowController {

    @Autowired
    private ShowService showService;
    @Operation(summary = "Create a new show")
    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        try{
            // Validate the show object (e.g., check for null values)
            if (show == null || show.getMovieId() == null || show.getScreenId() == null) {
                return ResponseEntity.badRequest().body(null);
            }
            Show createdShow = showService.createShow(show);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdShow);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }





/*    @GetMapping("/movie/{movieId}/town/{town}/date/{date}")
    public ResponseEntity<List<Show>> getShowsByMovieAndTownAndDate(
            @PathVariable Long movieId,
            @PathVariable String town,
            @PathVariable LocalDate date) {
        List<Show> shows = showService.getShowsByMovieAndTownAndDate(movieId, town, date);
        return ResponseEntity.ok(shows);
    }*/

/*    @GetMapping("/theatre/{theatreId}/date/{date}")
    public ResponseEntity<List<Show>> getShowsByTheatreAndDate(
            @PathVariable Long theatreId,
            @PathVariable LocalDate date) {
        List<Show> shows = showService.getShowsByTheatreAndDate(theatreId, date);
        return ResponseEntity.ok(shows);
    }*/

    @Operation(summary = "Get a show by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable Long id) {
        Optional<Show> show = showService.getShowById(id);
        if (show.isPresent()) {
            return ResponseEntity.ok(show.get());
        }
        return ResponseEntity.notFound().build();
    }

/*    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody Show show) {
        Show updatedShow = showService.updateShow(id, show);
        if (updatedShow != null) {
            return ResponseEntity.ok(updatedShow);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }*/
}
