package com.example.Grupp9.repository;

import com.example.Grupp9.model.Booking;
import com.example.Grupp9.model.Tyre;
import com.example.Grupp9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TyreRepository extends JpaRepository<Tyre, Long> {
    Optional<Tyre> findByType(String type);
}
