package com.booking.microservice.payment.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.booking.microservice.payment.exceptions.InvalidBookingIdException;
import com.booking.microservice.payment.exceptions.InvalidPaymentModelException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(InvalidBookingIdException.class)
    public ResponseEntity<String> handleInvalidBookingIdException(InvalidBookingIdException ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 400}",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPaymentModelException.class)
    public ResponseEntity<String> handleInvalidPaymentModeException(InvalidPaymentModelException ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 400}",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 500}",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
