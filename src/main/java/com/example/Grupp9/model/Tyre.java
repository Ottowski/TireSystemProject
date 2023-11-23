package com.example.Grupp9.model;

import jakarta.persistence.*;
import lombok.Data;

<<<<<<< HEAD
@Entity(name = "tyre")
@Data
=======
@Data
@Entity
@Table(name = "\"tyre\"")
>>>>>>> config-new
public class Tyre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD

    private String type;
=======
    private String type;
    private int amount;
    private double price;

    @ManyToOne
    private User user;
>>>>>>> config-new

    public Tyre() {
    }

    public Tyre(String type) {
        this.type = type;
    }
}