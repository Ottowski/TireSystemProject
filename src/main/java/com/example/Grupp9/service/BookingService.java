package com.example.Grupp9.service;

import com.example.Grupp9.model.Booking;
import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.model.User;
import com.example.Grupp9.repository.BookingRepository;
import com.example.Grupp9.repository.TyreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

//    public Booking newBooking(Booking booking) {
//        Optional<Tyre> tyreOptional = tyreRepository.findByType(booking.getType());
//
//        if (tyreOptional.isPresent()) {
//            Tyre tyre = tyreOptional.get();
//            int remainingTyreAmount = tyre.getAmount() - booking.getAmount();
//
//            if (remainingTyreAmount >= 0) {
//                // If enough tyres, update tyre amount and save booking
//                tyre.setAmount(remainingTyreAmount);
//                tyreRepository.save(tyre);
//                return bookingRepository.save(booking);
//            } else {
//                throw new RuntimeException("Not enough tyres available for booking");
//            }
//        } else {
//            throw new RuntimeException("Tyre not found for booking type: " + booking.getType());
//        }
//    }

    public void createNewBooking(User user, Tyre tyre, Booking book) {
        Booking booking = new Booking();
        Optional<Tyre> tyreOptional = tyreRepository.findByType(tyre.getType());

        Tyre tyre1 = tyreOptional.get();

        tyre1.setAmount(tyre1.getAmount() - book.getAmount());
        tyre1.setType(tyre.getType());
        tyre1.setPrice(tyre.getPrice());

        booking.setAmount(book.getAmount());
        booking.setTyre(tyre1);
        booking.setTotalPrice(tyre1.getPrice() * book.getAmount());
        booking.setDate(LocalDateTime.now());

        booking.setUser(user);
        booking.setTyre(tyre);

        user.getBooking().add(booking);

        bookingRepository.save(booking);


    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking findBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public void deleteBooking(Long id) {

        bookingRepository.deleteById(id);
    }
}
