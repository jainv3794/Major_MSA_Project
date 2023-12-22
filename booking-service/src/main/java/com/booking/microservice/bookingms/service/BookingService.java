package com.booking.microservice.bookingms.service;

import org.springframework.stereotype.Service;

import com.booking.microservice.bookingms.dto.BookingRequest;
import com.booking.microservice.bookingms.dto.PaymentRequest;
import com.booking.microservice.bookingms.entity.BookingInfoEntity;
import com.booking.microservice.bookingms.exceptions.InvalidBookingIdException;
import com.booking.microservice.bookingms.exceptions.InvalidPaymentModeException;
import com.booking.microservice.bookingms.repository.BookingRepository;

import java.util.Date;
import java.util.Random;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public BookingInfoEntity saveBooking(BookingRequest bookingRequest) {
        String roomNumbers = "";
        for (int i = 0; i < bookingRequest.getNumOfRooms(); i++) {
            roomNumbers += generateRandomRoomNumbers() + ",";
        }
        roomNumbers = roomNumbers.substring(0, roomNumbers.length() - 1);
        int roomPrice = calculateRoomPrice(bookingRequest.getNumOfRooms());

        BookingInfoEntity bookingInfo = new BookingInfoEntity();
        bookingInfo.setFromDate(bookingRequest.getFromDate());
        bookingInfo.setToDate(bookingRequest.getToDate());
        bookingInfo.setAadharNumber(bookingRequest.getAadharNumber());
        bookingInfo.setNumOfRooms(bookingRequest.getNumOfRooms());
        bookingInfo.setRoomNumbers(roomNumbers);
        bookingInfo.setRoomPrice(roomPrice);
        bookingInfo.setTransactionId(0);
        bookingInfo.setBookedOn(new Date());

        return bookingRepository.save(bookingInfo);
    }

    public void makePayment(Integer bookingId, PaymentRequest paymentRequest) {
        BookingInfoEntity bookingInfo = getBookingById(bookingId);

        if (bookingInfo == null) {
            throw new InvalidBookingIdException("Invalid Booking Id");
        }

        if (!isValidPaymentMode(paymentRequest.getPaymentMode())) {
            throw new InvalidPaymentModeException("Invalid mode of payment");
        }

        updateBooking(bookingInfo);
    }

    public Iterable<BookingInfoEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingInfoEntity getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    public void updateBooking(BookingInfoEntity bookingInfo) {
        bookingRepository.save(bookingInfo);
    }

    private String generateRandomRoomNumbers() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100) + 1);
    }

    private int calculateRoomPrice(int numOfRooms) {
        return 1000 * numOfRooms; // Replace with your logic
    }

    private boolean isValidPaymentMode(String paymentMode) {
        return "UPI".equalsIgnoreCase(paymentMode) || "CARD".equalsIgnoreCase(paymentMode);
    }
}
