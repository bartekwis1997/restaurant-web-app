package restaurantwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restaurantwebapp.model.Meal;
import restaurantwebapp.model.Restaurant;
import restaurantwebapp.service.RestaurantService;

import java.util.Set;
import java.util.UUID;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
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

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @DeleteMapping("/restaurants/{id}")
    public Restaurant deleteRestaurantById(@PathVariable UUID id) {
        return restaurantService.deleteRestaurantById(id);
    }

    @PostMapping("/restaurants/{id}/meals")
    public Meal addMealToRestaurant(@PathVariable UUID restaurantId, @RequestBody Meal meal) {
        return restaurantService.addMealToRestaurant(restaurantId, meal);
    }

    @DeleteMapping("/restaurants/{restaurantId}/meals/{mealId}")
    public Meal deleteMealFromRestaurant(@PathVariable UUID restaurantId, @PathVariable UUID mealId) {
        return restaurantService.deleteMealFromRestaurant(restaurantId, mealId);
    }

}
