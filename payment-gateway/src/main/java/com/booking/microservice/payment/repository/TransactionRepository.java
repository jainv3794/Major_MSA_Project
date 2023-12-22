package com.booking.microservice.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.microservice.payment.entity.TransactionDetailsEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetailsEntity, Integer> {

}
