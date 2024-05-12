package com.example.restaurantwebapp.service;

import com.example.restaurantwebapp.model.Meal;
import com.example.restaurantwebapp.model.Restaurant;
import com.example.restaurantwebapp.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant getRestaurantById(UUID uuid) {
        return restaurantRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    @Override
    public Set<Restaurant> getAllRestaurants() {
        return new HashSet<>(restaurantRepository.findAll());
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant deleteRestaurantById(UUID id) {
        Restaurant restaurant = getRestaurantById(id);
        restaurantRepository.deleteById(id);
        return restaurant;
    }

    @Override
    public Meal addMealToRestaurant(UUID restaurantId, Meal meal) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        restaurant.getMeals().add(meal);
        return meal;
    }

    @Override
    public Meal deleteMealFromRestaurant(UUID restaurantId, UUID mealId) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        Optional<Meal> mealOptional = restaurant.getMeals().stream()
                .filter(meal -> meal.getId().equals(mealId))
                .findFirst();

        if (mealOptional.isPresent()) {
            Meal deletedMeal = mealOptional.get();
            restaurant.getMeals().remove(deletedMeal);
            return deletedMeal;
        } else {
            throw new RuntimeException("Meal not found in the restaurant");
        }
    }

}
