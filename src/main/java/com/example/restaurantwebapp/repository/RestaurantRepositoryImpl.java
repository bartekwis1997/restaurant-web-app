package com.example.restaurantwebapp.repository;

import com.example.restaurantwebapp.model.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final Map<UUID, Restaurant> restaurantMap = new HashMap<>();

    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurantMap.values());
    }

    @Override
    public Optional<Restaurant> findById(UUID uuid) {
        return Optional.ofNullable(restaurantMap.get(uuid));
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            restaurant.setId(UUID.randomUUID());
        }
        restaurantMap.put(restaurant.getId(), restaurant);
        return restaurant;
    }

    @Override
    public UUID deleteById(UUID uuid) {
        restaurantMap.remove(uuid);
        return uuid;
    }
}
