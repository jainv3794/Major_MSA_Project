package com.booking.microservice.bookingms.exceptions;

public class InvalidPaymentModeException extends RuntimeException {

    public InvalidPaymentModeException(String message) {
        super(message);
    }
}
