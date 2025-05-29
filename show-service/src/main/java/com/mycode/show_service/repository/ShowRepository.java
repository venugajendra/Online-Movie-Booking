package com.mycode.show_service.repository;

import com.mycode.show_service.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    //List<Show> findByMovieAndShowDate(Movie movie, LocalDate showDate);

    List<Show> findByMovieIdAndShowDate(Long movieId, LocalDate date);

    List<Show> findByShowDate(LocalDate showDate);

//    List<Show> findByScreen_Theatre_IdAndShowDate(Long theatreId, LocalDate showDate);
}
