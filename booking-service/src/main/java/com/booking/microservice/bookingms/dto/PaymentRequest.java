package com.booking.microservice.bookingms.dto;

import lombok.Data;


@Data
public class PaymentRequest {
    private String paymentMode;
    private String upiId;
    private String cardNumber;
}
