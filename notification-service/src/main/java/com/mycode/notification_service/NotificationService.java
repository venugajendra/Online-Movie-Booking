package com.mycode.notification_service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendBookingConfirmation(String email, String bookingDetails) {
        System.out.println("Sending booking confirmation to " + email + ": " + bookingDetails);
        // Implement actual email/SMS sending logic here
    }
}
