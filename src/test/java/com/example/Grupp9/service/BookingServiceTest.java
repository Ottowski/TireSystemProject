package com.example.Grupp9.service;

import com.example.Grupp9.model.Booking;
import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.model.User;
import com.example.Grupp9.repository.BookingRepository;
import com.example.Grupp9.repository.TyreRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookingServiceTest {


    private AutoCloseable autoCloseable;


    @InjectMocks
    private BookingService bookingService;

    @Mock
    private TyreRepository tyreRepository;

    @Mock
    private BookingRepository bookingRepository;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        bookingService = new BookingService(bookingRepository, tyreRepository);
    }

    @AfterEach
    void tearDown() {
        try {
            autoCloseable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void afterAll() {
        System.out.println(" -->> after all");

    }


    @Test
    void testCreateNewBooking() {
        // Given
        User user = User.builder()
                .username("test")
                .password("test")
                .build();
        Tyre tyre = Tyre.builder()
                .type("ms")
                .amount(10)
                .price(100)
                .build();
        Booking book = new Booking();
        book.setAmount(1);
        book.setTyre(tyre);
        book.setUser(user);


        // When
        when(tyreRepository.save(any(Tyre.class))).thenAnswer(invocation -> invocation.getArgument(0));


        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArgument(0));


        bookingService.createNewBooking(user, tyre, book);

        // Then
        verify(tyreRepository, times(1)).save(any(Tyre.class));
        verify(bookingRepository, times(1)).save(any(Booking.class));
    }


    @Test
    void findAllBookings() {
//        Given
        when(bookingRepository.findAll()).thenReturn(List.of(
                new Booking(1, 1, 100, LocalDateTime.now(), new Tyre(), new User()),
                new Booking(2, 2, 200, LocalDateTime.now(), new Tyre(), new User()),
                new Booking(3, 3, 300, LocalDateTime.now(), new Tyre(), new User())
        ));

        List<Booking> bookings = bookingService.findAllBookings();

        // Then
        assertEquals(3, bookings.size());
        assertEquals(1, bookings.get(0).getId());
        assertEquals(2, bookings.get(1).getId());
        assertEquals(3, bookings.get(2).getId());


        verify(bookingRepository, times(1)).findAll();


    }

    @Test
    void deleteBooking() {
        // Given
        when(bookingRepository.existsById(1L)).thenReturn(true);

        // When
        bookingService.deleteBooking(1L);

        // Then
        verify(bookingRepository, times(1)).deleteById(1L);
    }
}