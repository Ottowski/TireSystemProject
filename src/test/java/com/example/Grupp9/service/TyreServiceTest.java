package com.example.Grupp9.service;

import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.repository.TyreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class TyreServiceTest {

    private AutoCloseable autoCloseable;


    @InjectMocks
    private TyreService tyreService;

    @Mock
    private TyreRepository tyreRepository;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        tyreService = new TyreService(tyreRepository);
    }

    @AfterEach
    void tearDown() {
        try {
            autoCloseable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void newTyre() {

        Tyre tyre = Tyre.builder()
                .type("Summer")
                .price(1000)
                .amount(4)
                .build();

        when(tyreRepository.save(any(Tyre.class))).thenReturn(tyre);

        Tyre savedTyre = tyreService.newTyre(tyre);

        assertEquals(tyre.getType(), savedTyre.getType());


    }

    @Test
    void findAllTyres() {

        // GIVEN
        List<Tyre> mockTyres = Arrays.asList(
                Tyre.builder()
                        .type("Summer")
                        .price(1000)
                        .amount(4)
                        .build(),
                Tyre.builder()
                        .type("Winter")
                        .price(2000)
                        .amount(4)
                        .build()
        );

        // WHEN
        when(tyreRepository.findAll()).thenReturn(mockTyres);


        List<Tyre> resultTyres = tyreService.findAllTyres();

        // THEN
        assertEquals(mockTyres, resultTyres);
        System.out.println(" -->> all tyres" + resultTyres);





    }

    @Test
    void findTireByType() {
        // Given
        String type = "someType";
        Tyre mockTyre = Tyre.builder()
                .type("someType")
                .amount(1)
                .build();


        when(tyreRepository.findByType(type)).thenReturn(Optional.of(mockTyre));

        // When
        Tyre resultTyre = tyreService.findTireByType(type);

        // Then
        assertEquals(mockTyre, resultTyre);
        System.out.println(" -->> tyre" + resultTyre);
    }

    @Test
    void updateNewTyre() {
        // GIVEN
        String type = "someType";
        Tyre inputTyre = Tyre.builder()
                .type("someType")
                .amount(1)
                .build();
        Tyre existingTyre = Tyre.builder()
                .type("someType2")
                .amount(10)
                .price(100)
                .build();


        when(tyreRepository.findByType(type)).thenReturn(Optional.of(existingTyre));
        when(tyreRepository.save(any(Tyre.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN
        Tyre resultTyre = tyreService.updateNewTyre(type, inputTyre);

        //THEN
        assertEquals(existingTyre.getType(), resultTyre.getType());
        assertEquals(existingTyre.getPrice(), resultTyre.getPrice());
        assertEquals(existingTyre.getAmount() + inputTyre.getAmount(), resultTyre.getAmount());


        System.out.println(" -->> before all 👉" + inputTyre);
        System.out.println(" -->> after all 👉" + existingTyre);
        System.out.println(" -->> after LAST update for amount" + resultTyre);
    }


    @Test
    void deleteTyre() {
       //Given
        String type = "someType";
        Tyre existingTyre = Tyre.builder()
                .type("someType")
                .amount(10)
                .price(100)
                .build();

        // WHEN
        when(tyreRepository.findByType(type)).thenReturn(Optional.of(existingTyre));


        tyreService.deleteTyre(type);

       // THEN
        verify(tyreRepository, times(1)).findByType(type);
        verify(tyreRepository, times(1)).delete(existingTyre);

    }
}