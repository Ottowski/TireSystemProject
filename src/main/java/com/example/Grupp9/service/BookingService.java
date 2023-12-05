package com.example.Grupp9.service;

import com.example.Grupp9.exception.NotFoundException;
import com.example.Grupp9.model.Booking;
import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.model.User;
import com.example.Grupp9.repository.BookingRepository;
import com.example.Grupp9.repository.TyreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TyreRepository tyreRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, TyreRepository tyreRepository) {
        this.bookingRepository = bookingRepository;
        this.tyreRepository = tyreRepository;
    }


    public void createNewBooking(User user, Tyre tyre, Booking book) {
        //        create new booking object
        Booking booking = new Booking();
       //        create new tyre object
        var newTyre = new Tyre();

        //        get tyre from db
        newTyre.setAmount(newTyre.getAmount() - book.getAmount());
        newTyre.setType(tyre.getType());
        newTyre.setPrice(tyre.getPrice());

        //        set booking object
        booking.setAmount(book.getAmount());
        booking.setTyre(newTyre);
        booking.setTotalPrice(newTyre.getPrice() * book.getAmount());
        booking.setDate(LocalDateTime.now());

        //        set user object and add booking to user
        booking.setUser(user);
        booking.setTyre(tyre);
        user.getBooking().add(booking);

        //        save booking
        bookingRepository.save(booking);


    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }


    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}


