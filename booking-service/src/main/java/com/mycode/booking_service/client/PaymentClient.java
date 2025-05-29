package com.mycode.booking_service.client;

import com.mycode.booking_service.dto.PaymentRequestDTO;
import com.mycode.payment_service.exception.PaymentFailedException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service") // url = "http://localhost:9090") // Replace with actual payment-service URL or service discovery name
public interface PaymentClient {
    @PostMapping("/payments/process")
    public void processPayment(@RequestBody PaymentRequestDTO request) throws PaymentFailedException;;
}