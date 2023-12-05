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
    public Tyre() {
    }


    public Tyre(String type, int amount, double price) {
        this.type = type;
        this.amount = amount;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Tyre{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}