package com.booking.microservice.payment.exceptions;

public class InvalidBookingIdException extends RuntimeException {
    public InvalidBookingIdException(String message) {
        super(message);
    }
}
