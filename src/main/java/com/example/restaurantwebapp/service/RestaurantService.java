package com.example.restaurantwebapp.service;

import com.example.restaurantwebapp.model.Meal;
import com.example.restaurantwebapp.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public interface RestaurantService {

    Restaurant getRestaurantById(UUID restaurantId);

    Set<Restaurant> getAllRestaurants();

    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant deleteRestaurantById(UUID restaurantId);

    Meal addMealToRestaurant(UUID restaurantId, Meal meal);

    Meal deleteMealFromRestaurant(UUID restaurantId, UUID mealId);

}
