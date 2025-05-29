package com.mycode.payment_service.service;

import com.mycode.payment_service.exception.PaymentFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {

    public void processPayment(Long bookingId, double amount) throws PaymentFailedException {
        // Simulate payment processing logic
        log.info("Processing payment for bookingId: {}, amount: {}", bookingId, amount);
        log.info("Inside the processPayment method of PaymentService.");
        // Simulate success/failure
        if (Math.random() < 0.8) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new PaymentFailedException("Payment processing interrupted.");
            }
            System.out.println("Payment of " + amount + " successful for booking " + bookingId);
        } else {
            System.out.println("Payment of " + amount + " failed for booking " + bookingId);
            throw new PaymentFailedException("Payment failed due to an error.");
        }
    }


}

