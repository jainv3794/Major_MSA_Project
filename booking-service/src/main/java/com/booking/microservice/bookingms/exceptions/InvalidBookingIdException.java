package com.booking.microservice.bookingms.exceptions;

public class InvalidBookingIdException extends RuntimeException {

    public InvalidBookingIdException(String message) {
        super(message);
    }
}
