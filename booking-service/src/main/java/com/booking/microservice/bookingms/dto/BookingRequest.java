package com.booking.microservice.bookingms.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BookingRequest {

    private Date fromDate;

    private Date toDate;


    private String aadharNumber;


    private int numOfRooms;
}
