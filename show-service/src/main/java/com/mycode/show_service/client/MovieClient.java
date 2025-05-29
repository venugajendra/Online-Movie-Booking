package com.mycode.show_service.client;

import com.mycode.show_service.dto.MovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "movie-service")
public interface MovieClient {

    @GetMapping("/movies/{id}")
    MovieDTO getMovieById(Long id);
}


/*@HttpExchange("/movies")
public interface MovieClient {
    @GetExchange("/{id}")
    MovieDTO getMovieById(@PathVariable("id") Long id);
}*/

