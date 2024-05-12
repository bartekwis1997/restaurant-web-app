package com.example.restaurantwebapp.controller;

import com.example.restaurantwebapp.model.Meal;
import com.example.restaurantwebapp.model.Restaurant;
import org.springframework.web.bind.annotation.*;
import com.example.restaurantwebapp.service.RestaurantService;

import java.util.Set;
import java.util.UUID;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable UUID id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/restaurants")
    public Set<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PostMapping("/addrestaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @DeleteMapping("/restaurants/{id}")
    public Restaurant deleteRestaurantById(@PathVariable UUID id) {
        return restaurantService.deleteRestaurantById(id);
    }

    @PostMapping("/restaurants/{restaurantId}/meals")
    public Meal addMealToRestaurant(@PathVariable UUID restaurantId, @RequestBody Meal meal) {
        return restaurantService.addMealToRestaurant(restaurantId, meal);
    }

    @DeleteMapping("/restaurants/{restaurantId}/meals/{mealId}")
    public Meal deleteMealFromRestaurant(@PathVariable UUID restaurantId, @PathVariable UUID mealId) {
        return restaurantService.deleteMealFromRestaurant(restaurantId, mealId);
    }

}
