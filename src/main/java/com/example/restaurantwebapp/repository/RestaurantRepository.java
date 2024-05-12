package com.example.restaurantwebapp.repository;

import com.example.restaurantwebapp.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository {

    List<Restaurant> findAll();

    Optional<Restaurant> findById(UUID uuid);

    Restaurant save(Restaurant restaurant);

    UUID deleteById(UUID uuid);
}
