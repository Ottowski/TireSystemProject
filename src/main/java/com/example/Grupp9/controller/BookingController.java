package com.example.Grupp9.controller;

import com.example.Grupp9.model.Booking;
import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.model.User;
import com.example.Grupp9.repository.TyreRepository;
import com.example.Grupp9.repository.UserRepo;
import com.example.Grupp9.service.BookingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;
    private final UserRepo userRepo;

    private final TyreRepository tyreRepository;

    @Autowired
    public BookingController(BookingService bookingService, UserRepo userRepo, TyreRepository tyreRepository) {
        this.bookingService = bookingService;
        this.userRepo = userRepo;
        this.tyreRepository = tyreRepository;
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingService.findAllBookings();
    }

    // URL: http://localhost:8081/api/bookings
    // Token Needed

//    @PostMapping("/bookings")
//    public Booking newBooking(@RequestBody Booking booking) {
//        return bookingService.newBooking(booking);
//    }

    // URL: http://localhost:8081/api/bookings
    // Token Needed
    /* EXAMPLE json:
        {
	"type": "Winter",
	"amount": 4,
	"date": "2007-12-03T10:15:30"
}
	*/

    @PostMapping("/booking/{userId}/{tyreType}")
    public ResponseEntity<String> bookingUser(@PathVariable Long userId, @PathVariable String tyreType, @RequestBody Booking booking){

        User user = userRepo.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("User Not Found"));

        Tyre tyre = tyreRepository.findByType(tyreType)
                .orElseThrow(()-> new EntityNotFoundException("User Not Found"));

        bookingService.createNewBooking(user, tyre, booking);

        return ResponseEntity.ok("Booking successful");

    }


}
