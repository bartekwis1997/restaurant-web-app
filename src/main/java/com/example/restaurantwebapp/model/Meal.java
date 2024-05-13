package com.example.restaurantwebapp.model;

import java.util.UUID;

public class Meal {

    private UUID id;
    private String name;
    private double price;

    public Meal(String name, double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
