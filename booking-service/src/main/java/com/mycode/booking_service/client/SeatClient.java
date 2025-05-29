package com.mycode.booking_service.client;


import com.mycode.booking_service.dto.SeatDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "booking-service")
public interface SeatClient {

    @GetMapping("/seats/{id}")
    SeatDTO getSeat(@PathVariable("id") Long id);





}
