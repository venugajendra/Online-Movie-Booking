package com.mycode.booking_service.service;

import com.mycode.booking_service.client.PaymentClient;
import com.mycode.booking_service.client.SeatClient;
import com.mycode.booking_service.client.ShowClient;
import com.mycode.booking_service.client.UserClient;
import com.mycode.booking_service.dto.PaymentRequestDTO;
import com.mycode.booking_service.dto.SeatDTO;
import com.mycode.booking_service.dto.ShowDTO;
import com.mycode.booking_service.dto.UserDTO;
import com.mycode.booking_service.exception.*;
import com.mycode.booking_service.model.Booking;
import com.mycode.booking_service.model.PaymentStatus;
import com.mycode.booking_service.repository.BookingRepository;
import feign.FeignException;
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
            throw new InvalidBookingRequestException("User ID, Show ID, and Seat IDs are required.");
        }


        // 2. Fetch data from other services with robust error handling
        UserDTO user = null;
        try {
            user = userClient.getUser(userId);
            if (user == null) {
                log.error("User with ID {} not found.", userId);
                throw new InvalidBookingRequestException("User with ID " + userId + " not found.");
            }
        } catch (FeignException e) {
            log.error("Failed to fetch user with ID {}. External service error: {}", userId, e.getMessage());
            // Using the constructor with cause
            throw new UserServiceClientException("Failed to fetch", e);
        } catch (Exception e) {
            log.error("An unexpected error occurred while fetching user {}: {}", userId, e.getMessage());
            throw new BookingServiceException("An unexpected error occurred while fetching user " + userId, e);
        }

        ShowDTO show = null;
        try {
            show = showClient.getShow(showId);
            if (show == null) {
                log.error("Show with ID {} not found.", showId);
                throw new ShowUnavailableException("Show with ID " + showId + " not found.");
            }
        } catch (FeignException e) {
            log.error("Failed to fetch show with ID {}. External service error: {}", showId, e.getMessage());
            throw new ShowServiceClientException(e);
        } catch (Exception e) {
            log.error("An unexpected error occurred while fetching show {}: {}", showId, e.getMessage());
            throw new BookingServiceException("An unexpected error occurred while fetching show " + showId, e);
        }

        List<SeatDTO> seatDTOs = null;
        try {
            seatDTOs = seatIds.stream()
                    .map(seatId -> {
                        try {
                            SeatDTO seat = seatClient.getSeat(seatId);
                            if (seat == null) {
                                log.warn("Seat with ID {} not found or invalid.", seatId);
                                throw new SeatNotAvailableException("Seat with ID " + seatId + " not found or invalid.");
                            }
                            // You might want to add availability check here if `getSeat` doesn't do it
                            // e.g., if (!seat.isAvailable()) throw new SeatNotAvailableException("Seat " + seatId + " is already booked.");
                            return seat;
                        } catch (FeignException e) {
                            log.error("Failed to fetch seat with ID {}. External service error: {}", seatId, e.getMessage());
                            throw new SeatServiceClientException(e);
                        }
                    })
                    .collect(Collectors.toList());

            if (seatDTOs.size() != seatIds.size()) {
                log.warn("Mismatch in requested and retrieved seats. Some seats might be invalid.");
                throw new InvalidBookingRequestException("One or more seat IDs are invalid or not found.");
            }
            // Further check for actual seat availability (if SeatDTO has a status)
            // Assuming SeatDTO has an `isAvailable()` method
            /*boolean anySeatNotAvailable = seatDTOs.stream().anyMatch(seat -> !seat.isAvailable());
            if (anySeatNotAvailable) {
                log.warn("One or more selected seats are not available.");
                throw new SeatNotAvailableException("One or more selected seats are not available.");
            }*/

        } catch (SeatNotAvailableException | SeatServiceClientException | InvalidBookingRequestException e) {
            throw e; // Re-throw specific seat exceptions
        } catch (Exception e) {
            log.error("An unexpected error occurred while fetching seats: {}", e.getMessage());
            throw new BookingServiceException("An unexpected error occurred while fetching seats.", e);
        }

        // 3. Validate show date
        if (show.getShowDate().isBefore(LocalDate.now())) {
            log.warn("Attempt to book tickets for a past show (ID: {}).", showId);
            throw new PastShowBookingException("Cannot book tickets for a past show.");
        }

       /* // 2. Fetch data from other services
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
        }*/




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

        log.debug("Calculated total price: {} for show ID: {} with {} seats.", totalPrice, showId, seatIds.size());

        //log.info("Initial booking created with ID: {} and status: {}", booking.getId(), booking.getPaymentStatus());


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
            log.info("Attempting to process payment for booking ID: {} with amount: {}", booking.getId(), totalPrice);
            paymentClient.processPayment(request);
            booking.setPaymentStatus(PaymentStatus.SUCCESSFUL);
            log.info("Payment successful for booking ID: {}", booking.getId());
        } catch (FeignException e) {
            log.error("Payment failed for booking ID {} due to external payment service error: {}", booking.getId(), e.getMessage());
            booking.setPaymentStatus(PaymentStatus.FAILED);
            bookingRepository.save(booking);
            throw new PaymentServiceException("Payment failed" + e.getMessage()); // Use constructor with cause
        } catch (Exception e) {
            log.error("An unexpected error occurred during payment for booking ID {}: {}", booking.getId(), e.getMessage());
            booking.setPaymentStatus(PaymentStatus.FAILED);
            bookingRepository.save(booking);
            throw new BookingServiceException("Payment failed", e); // Use constructor with cause
        }


/*        } catch (Exception e) {
            booking.setPaymentStatus(PaymentStatus.FAILED);
            bookingRepository.save(booking);

            System.out.println("booking.getId(): " + booking.getId());
            System.out.println("totalPrice: " + totalPrice);
            System.out.println("Booking Service Error: " + e.getMessage());

            throw new RuntimeException("Payment failed: " + e.getMessage());
        }*/



        // 8. Final save
        return bookingRepository.save(booking);
    }

        public Booking getBookingById(Long id) {
            log.info("Fetching booking with ID: {}", id);
            return bookingRepository.findById(id)
                    .orElseThrow(() -> {
                        log.warn("Booking with ID {} not found.", id);
                        // Use the default message for the error code, or provide a specific message
                        throw new InvalidBookingRequestException("Booking with ID " + id + " not found.");
                    });
        }
}

