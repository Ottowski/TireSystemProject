package com.example.Grupp9.controller;

import com.example.Grupp9.JwtConfig.JwtUtil;
import com.example.Grupp9.exception.NotFoundException;
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

    private final JwtUtil jwtUtil;

    @Autowired
    public BookingController(BookingService bookingService, UserRepo userRepo, TyreRepository tyreRepository, JwtUtil jwtUtil) {
        this.bookingService = bookingService;
        this.userRepo = userRepo;
        this.tyreRepository = tyreRepository;
        this.jwtUtil = jwtUtil;
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

    // URL: http://localhost:8081/api/booking/{tyreType}
    // Token Needed
    /* EXAMPLE json:
        {
            "amount": 4
        }
	*/

    @PostMapping("/booking/{tyreType}")
    public ResponseEntity<String> bookingUser(@RequestHeader("Authorization") String userId, @PathVariable String tyreType, @RequestBody Booking booking) {

        userId = userId.substring(7);
        var token = jwtUtil.getSubject(userId);

        User user = userRepo.findByUsername(token)
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));

        Tyre tyre = tyreRepository.findByType(tyreType)
                .orElseThrow(() -> new EntityNotFoundException("Tyre Not Found"));

        System.out.println("tyre: ✌️ " + tyre.toString());
        bookingService.createNewBooking(user, tyre, booking);

        return ResponseEntity.ok("Booking successful");

    }

    @GetMapping("/available-tyres")
    public ResponseEntity<List<Tyre>> getAvailableTyres() {
        List<Tyre> tyres = tyreRepository.findAll();
        return ResponseEntity.ok(tyres);
    }
<<<<<<< Updated upstream
@PostMapping("/create-booking")
public ResponseEntity<?> createBooking(@RequestBody Booking booking) {
    Tyre tyre = tyreRepository.findByType(booking.getTyre().getType())
            .orElseThrow(() -> new EntityNotFoundException("Tyre not found"));
    booking.setTyre(tyre);
    bookingService.save(booking);
    return ResponseEntity.ok("Booking created successfully");
}
//logga in sedan http://localhost:8081/api/booking/Winter
//    {
//        "amount": 4
//    }
//
=======
>>>>>>> Stashed changes

    @DeleteMapping("/booking/{id}")
    public ResponseEntity<String> deleteBookingFromDB(@PathVariable Long id) {

        bookingService.deleteBooking(id);


        return ResponseEntity.ok("Delete booking " + id + " successfully");

    }


}
