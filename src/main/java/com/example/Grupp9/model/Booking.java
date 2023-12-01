package com.example.Grupp9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "\"booking\"")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;
    private double totalPrice;
    private LocalDateTime date;

    @ManyToOne
//    @JsonIgnore
    private Tyre tyre;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Booking() {
    }

    public Booking(int amount, double totalPrice, LocalDateTime date, Tyre tyre, User user) {
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.date = date;
        this.tyre = tyre;
        this.user = user;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
