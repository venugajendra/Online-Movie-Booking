package com.mycode.payment_service.controller;

import com.mycode.payment_service.dto.PaymentRequestDTO;
import com.mycode.payment_service.service.PaymentService;
import com.mycode.payment_service.exception.PaymentFailedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/payments")
@Tag(name = "Payment", description = "APIs related to payment processing")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Operation(summary = "Process a payment for a booking")
    @PostMapping("/process")
    public ResponseEntity<String> processPayment(
            @RequestBody PaymentRequestDTO request) {
        log.info("Inside the processPayment method of PaymentController.");
        try {
            System.out.println("Received bookingId: " + request.getBookingId() + ", amount: " + request.getAmount());
            // Validate the request
            paymentService.processPayment(request.getBookingId(), request.getAmount());
            return ResponseEntity.ok("Payment processed successfully for booking ID: " + request.getBookingId());
        } catch (PaymentFailedException e) {
            return ResponseEntity.badRequest()
                    .body("Payment failed: " + e.getMessage());
        }
    }
}
