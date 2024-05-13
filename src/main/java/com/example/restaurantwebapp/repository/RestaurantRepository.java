package com.example.restaurantwebapp.repository;

import com.example.restaurantwebapp.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RestaurantRepository {

    Set<Restaurant> findAll();

    Optional<Restaurant> findById(UUID uuid);

    Restaurant saveRestaurant(Restaurant restaurant);

    UUID deleteById(UUID uuid);
}
