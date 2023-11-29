package com.example.Grupp9.service;

import com.example.Grupp9.model.Booking;
import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.repository.BookingRepository;
import com.example.Grupp9.repository.TyreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TyreRepository tyreRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, TyreRepository tyreRepository) {
        this.bookingRepository = bookingRepository;
        this.tyreRepository = tyreRepository;
    }

    public Booking newBooking(Booking booking) {
        Optional<Tyre> tyreOptional = tyreRepository.findByType(booking.getType());

        if (tyreOptional.isPresent()) {
            Tyre tyre = tyreOptional.get();
            int remainingTyreAmount = tyre.getAmount() - booking.getAmount();

            if (remainingTyreAmount >= 0) {
                // If enough tyres, update tyre amount and save booking
                tyre.setAmount(remainingTyreAmount);
                tyreRepository.save(tyre);
                return bookingRepository.save(booking);
            } else {
                throw new RuntimeException("Not enough tyres available for booking");
            }
        } else {
            throw new RuntimeException("Tyre not found for booking type: " + booking.getType());
        }
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking findBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }
}
