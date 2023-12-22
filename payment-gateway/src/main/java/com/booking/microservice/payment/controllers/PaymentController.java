package com.booking.microservice.payment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.booking.microservice.payment.dto.PaymentRequest;
import com.booking.microservice.payment.entity.TransactionDetailsEntity;
import com.booking.microservice.payment.exceptions.InvalidBookingIdException;
import com.booking.microservice.payment.exceptions.InvalidPaymentModelException;
import com.booking.microservice.payment.exceptions.TransactionNotFoundException;
import com.booking.microservice.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/transaction")
    public ResponseEntity<String> makePayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            int transactionId = paymentService.makePayment(paymentRequest);
            return new ResponseEntity<>("Transaction successful. Transaction ID: " + transactionId, HttpStatus.OK);
        } catch (InvalidBookingIdException | InvalidPaymentModelException ex) {
            return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 400}",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<TransactionDetailsEntity> getTransactionDetails(@PathVariable int transactionId) {
        try {
            TransactionDetailsEntity transactionDetails = paymentService.getTransactionDetails(transactionId);
            return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
        } catch (TransactionNotFoundException ex) {
            throw new TransactionNotFoundException("Transaction not found");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("{\"message\": \"" + ex.getMessage() + "\", \"statusCode\": 500}",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
