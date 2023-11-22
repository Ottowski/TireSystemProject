package com.example.Grupp9.model;

import jakarta.persistence.*;
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

    public Tyre(String type) {
        this.type = type;
    }
}