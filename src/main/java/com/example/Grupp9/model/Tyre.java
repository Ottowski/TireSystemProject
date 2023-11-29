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
    private String type;
    private int amount;
    private double price;

    @ManyToOne
    private User user;

    public Tyre() {
    }

}