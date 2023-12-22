package com.booking.microservice.bookingms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.microservice.bookingms.entity.BookingInfoEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingInfoEntity, Integer> {
}