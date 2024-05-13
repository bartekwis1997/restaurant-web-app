package com.example.restaurantwebapp.initializer;

import org.springframework.boot.CommandLineRunner;
import com.example.restaurantwebapp.model.Restaurant;
import com.example.restaurantwebapp.model.RestaurantType;
import com.example.restaurantwebapp.model.Meal;
import com.example.restaurantwebapp.service.RestaurantService;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RestaurantService restaurantService;

    public DataInitializer(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeData();
    }

    public Set<Restaurant> initializeData() {
        Set<Restaurant> restaurants = new HashSet<>();

        Restaurant restaurant1 = new Restaurant(UUID.randomUUID(), "Restaurant 1", "Address 1", RestaurantType.ASIAN);
        restaurant1.getMeals().add(new Meal("Meal 1", 10.0));
        restaurant1.getMeals().add(new Meal("Meal 2", 15.0));
        restaurants.add(restaurant1);

        Restaurant restaurant2 = new Restaurant(UUID.randomUUID(), "Restaurant 2", "Address 2", RestaurantType.ITALIAN);
        restaurant2.getMeals().add(new Meal("Meal 3", 12.0));
        restaurant2.getMeals().add(new Meal("Meal 4", 18.0));
        restaurants.add(restaurant2);

        Restaurant restaurant3 = new Restaurant(UUID.randomUUID(), "Restaurant 3", "Address 3", RestaurantType.AMERICAN);
        restaurant3.getMeals().add(new Meal("Meal 5", 9.0));
        restaurant3.getMeals().add(new Meal("Meal 6", 16.0));
        restaurants.add(restaurant3);

        for (Restaurant restaurant : restaurants) {
            restaurantService.addRestaurant(restaurant);
        }

        return restaurants;
    }
}
