package com.mycode.booking_service.client;

import com.mycode.booking_service.dto.ShowDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "show-service")
public interface ShowClient {

    @GetMapping("/shows/{id}")
    ShowDTO getShow(@PathVariable("id") Long showId);
/*    {
        // Simulate a call to the Show Service
        ShowDTO show = new ShowDTO();
        show.setId(showId);
        show.setMovieName("Inception");
        show.setStartTime(LocalDateTime.now());
        show.setEndTime(LocalDateTime.now().plusHours(2));
        show.setTheatreName("IMAX");
        return show;
    }*/
}
