package restaurantwebapp.service;

import restaurantwebapp.model.Meal;
import restaurantwebapp.model.Restaurant;

import java.util.Set;
import java.util.UUID;

public interface RestaurantService {

    Restaurant getRestaurantById(UUID restaurantId);

    Set<Restaurant> getAllRestaurants();

    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant deleteRestaurantById(UUID restaurantId);

    Meal addMealToRestaurant(UUID restaurantId, Meal meal);

    Meal deleteMealFromRestaurant(UUID restaurantId, UUID mealId);

}
