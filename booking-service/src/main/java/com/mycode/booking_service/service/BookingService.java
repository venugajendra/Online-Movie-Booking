package com.mycode.booking_service.service;

import com.mycode.booking_service.client.PaymentClient;
import com.mycode.booking_service.client.SeatClient;
import com.mycode.booking_service.client.ShowClient;
import com.mycode.booking_service.client.UserClient;
import com.mycode.booking_service.dto.PaymentRequestDTO;
import com.mycode.booking_service.dto.SeatDTO;
import com.mycode.booking_service.dto.ShowDTO;
import com.mycode.booking_service.dto.UserDTO;
import com.mycode.booking_service.model.Booking;
import com.mycode.booking_service.model.PaymentStatus;
import com.mycode.booking_service.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Removed UserService, ShowService, and SeatService
    @Autowired
    private PaymentClient paymentClient; // Added PaymentService

    @Autowired
    private UserClient userClient;

    @Autowired
    private ShowClient showClient;

    @Autowired
    private SeatClient seatClient;


    @Transactional
    public Booking bookTickets(Long userId, Long showId, List<Long> seatIds) {
        // Log the booking request
        log.info("Inside the bookTickets method of BookingService.");
        log.info("Booking tickets for userId: {}, showId: {}, seatIds: {}", userId, showId, seatIds);
        // 1. Validate inputs
        if (userId == null || showId == null || seatIds == null || seatIds.isEmpty()) {
            throw new IllegalArgumentException("User ID, Show ID, and Seat IDs are required.");
        }

        // 2. Fetch data from other services
        UserDTO user = userClient.getUser(userId);
        ShowDTO show = showClient.getShow(showId);
        List<SeatDTO> seatDTOs = seatIds.stream()
                .map(seatClient::getSeat)
                .collect(Collectors.toList());

        // 3. Validate show date
        if (show.getShowDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot book tickets for a past show.");
        }

        // 4. Validate seat ownership and availability (mocked/assumed here)
        if (seatDTOs.size() != seatIds.size()) {
            throw new IllegalArgumentException("One or more seat IDs are invalid.");
        }

        // 5. Calculate total price
/*        double totalPrice = seatDTOs.stream()
                .mapToDouble(SeatDTO::getPrice)
                .sum();*/

        double ticketPrice = show.getTicketPrice();
        double totalPrice = 0;

        // --------------------
        // Apply Discounts
        // --------------------

        // Apply 50% discount on the 3rd ticket (only once)
        for (int i = 0; i < seatIds.size(); i++) {
            if (i == 2) { // 3rd ticket (0-indexed)
                totalPrice += ticketPrice * 0.5;
            } else {
                totalPrice += ticketPrice;
            }
        }

        // Apply 20% discount for afternoon shows (between 12PM and 4PM)
        if (show.getShowTime().isAfter(LocalTime.NOON) && show.getShowTime().isBefore(LocalTime.of(16, 0))) {
            totalPrice *= 0.8; // Apply 20% discount
        }



        // 6. Create and save initial booking
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setShowId(showId);
        booking.setSeatIds(seatIds);
        booking.setBookingTime(LocalDateTime.now());
        booking.setTotalPrice(totalPrice);
        booking.setPaymentStatus(PaymentStatus.PENDING);

        //booking = bookingRepository.save(booking);

/*        // 7. Attempt payment
        try {
            paymentClient.processPayment(booking.getId(), totalPrice);
            booking.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        } catch (Exception e) { // General exception handling
            booking.setPaymentStatus(PaymentStatus.FAILED);
            bookingRepository.save(booking);
            System.out.println("booking.getId()" + booking.getId());
            System.out.println("totalPrice" + totalPrice);
            System.out.println("Hello booking service: " + e.getMessage());
            throw new RuntimeException("Payment failed hello: " + e.getMessage());
        }*/

        // 7. Attempt payment
        try {
            PaymentRequestDTO request = new PaymentRequestDTO();
            request.setBookingId(booking.getId());
            request.setAmount(totalPrice);

            paymentClient.processPayment(request);
            booking.setPaymentStatus(PaymentStatus.SUCCESSFUL);
        } catch (Exception e) {
            booking.setPaymentStatus(PaymentStatus.FAILED);
            bookingRepository.save(booking);

            System.out.println("booking.getId(): " + booking.getId());
            System.out.println("totalPrice: " + totalPrice);
            System.out.println("Booking Service Error: " + e.getMessage());

            throw new RuntimeException("Payment failed: " + e.getMessage());
        }



        // 8. Final save
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
}
