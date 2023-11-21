package com.example.Grupp9.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "tyre")
@Data
public class Tyre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    public Tyre() {
    }

    public Tyre(Long id, String name, String type) {
        this.id = id;
        this.type = type;
    }
}