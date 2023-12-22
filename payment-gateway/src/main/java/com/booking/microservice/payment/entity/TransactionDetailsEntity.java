package com.booking.microservice.payment.entity;

import lombok.Data;

/**
 * The Jakarta Persistence API provides a set of Java classes and interfaces that define the Java Persistence specification.
 * It is used for managing relational data in Java applications.
 * The @Entity annotation is used to specify that a class is an entity and is mapped to a database table.
 * The @Table annotation is used to specify the details of the table that will be used to persist the entity in the database.
 * The @Column annotation is used to specify the details of the column that will be used to persist the attribute in the database.
 * The @Id annotation is used to specify the primary key of an entity.
 * The @GeneratedValue annotation is used to specify the generation strategy for the primary key.
 */
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
public class TransactionDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Column(nullable = false)
    private String paymentMode;

    @Column(nullable = false)
    private int bookingId;

    private String upiId;

    private String cardNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
}
