package com.example.restaurantwebapp.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Restaurant {

    private UUID id;
    private String name;
    private final String address;
    private final RestaurantType type;
    private Set<Meal> meals = new HashSet<>();

    public Restaurant(UUID id, String name, String address, RestaurantType type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public RestaurantType getType() {
        return type;
    }
}
