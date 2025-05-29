package com.mycode.show_service.client;

import com.mycode.show_service.dto.ScreenDTO;
import com.mycode.show_service.dto.TheatreDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/*@FeignClient(name = "theatre-service", path = "/screens")
public interface TheatreClient {

    @GetMapping("/{id}")
    ScreenDTO getScreenById(@PathVariable Long id);

}*/


@FeignClient(name = "theatre-service")
public interface TheatreClient {

    @GetMapping("/theatres/location/{town}")
    List<TheatreDTO> getTheatresByLocation(@PathVariable("town") String town);

    @GetMapping("/screens/{id}")
    ScreenDTO getScreenById(@PathVariable("id") Long screenId);
}