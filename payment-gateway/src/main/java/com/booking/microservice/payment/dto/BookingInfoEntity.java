package com.booking.microservice.payment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
public class BookingInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date fromDate;

    @Column(nullable = false)
    private Date toDate;

    @Column(nullable = false)
    private String aadharNumber;

    @Column(nullable = false)
    private int numOfRooms;

    @Column(nullable = false)
    private String roomNumbers;

    @Column(nullable = false)
    private int roomPrice;

    @Column(nullable = false)
    private int transactionId = 0;

    @Column(nullable = false)
    private Date bookedOn;
}
