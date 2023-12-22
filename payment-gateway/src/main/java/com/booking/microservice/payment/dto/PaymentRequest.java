package com.booking.microservice.payment.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String paymentMode;
    private int bookingId;
    private String upiId;
    private String cardNumber;
}
