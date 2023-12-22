package com.booking.microservice.bookingms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.booking.microservice.bookingms.dto.BookingRequest;
import com.booking.microservice.bookingms.dto.PaymentRequest;
import com.booking.microservice.bookingms.entity.BookingInfoEntity;
import com.booking.microservice.bookingms.exceptions.InvalidBookingIdException;
import com.booking.microservice.bookingms.exceptions.InvalidPaymentModeException;
import com.booking.microservice.bookingms.service.BookingService;


@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingInfoEntity> getBookingById(@PathVariable Integer bookingId) {
        BookingInfoEntity bookingInfo = bookingService.getBookingById(bookingId);

        if (bookingInfo == null) {
            throw new InvalidBookingIdException("Invalid Booking Id");
        }

        return new ResponseEntity<>(bookingInfo, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Iterable<BookingInfoEntity>> getAllBookings() {
        Iterable<BookingInfoEntity> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookingInfoEntity> createBooking(@RequestBody BookingRequest bookingRequest) {
        BookingInfoEntity savedBooking = bookingService.saveBooking(bookingRequest);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    @PostMapping("/{bookingId}/transaction")
    public ResponseEntity<String> makePayment(@PathVariable Integer bookingId,
            @RequestBody PaymentRequest paymentRequest) {
        try {
            bookingService.makePayment(bookingId, paymentRequest);
            return new ResponseEntity<>("Transaction successful. Booking confirmed.", HttpStatus.OK);
        } catch (InvalidBookingIdException | InvalidPaymentModeException ex) {
            return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 400}",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 500}",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
