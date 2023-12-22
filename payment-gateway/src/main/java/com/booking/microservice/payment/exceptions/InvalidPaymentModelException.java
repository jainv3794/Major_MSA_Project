package com.booking.microservice.payment.exceptions;

public class InvalidPaymentModelException extends RuntimeException {

    public InvalidPaymentModelException(String message) {
        super(message);
    }
}
