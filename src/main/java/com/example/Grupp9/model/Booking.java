package com.example.Grupp9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
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

    public Booking(int id, int amount, double totalPrice, LocalDateTime date, Tyre tyre, User user) {
        this.id = id;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.date = date;
        this.tyre = tyre;
        this.user = user;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

@Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                ", tyre=" + tyre +
                ", user=" + user +
                '}';
    }
}
