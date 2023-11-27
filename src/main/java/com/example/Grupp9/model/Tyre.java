package com.example.Grupp9.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@Entity
@Table(name = "\"tyre\"")
public class Tyre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int amount;
    private double price;

    @ManyToOne
    private User user;

    public Tyre() {
    }

    public Tyre(String tyre) {
        this.type = tyre;
    }
//    Tyre winterTyre = new Tyre("Winter");
//    Tyre summerTyre = new Tyre("Summer");

}