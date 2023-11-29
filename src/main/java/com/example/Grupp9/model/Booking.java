package com.example.Grupp9.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "\"booking\"")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private int amount;
    private Date date;

    @ManyToOne
    private User user;

    public Booking() {
    }
}
