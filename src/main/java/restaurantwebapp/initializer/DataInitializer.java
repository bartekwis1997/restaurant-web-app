package restaurantwebapp.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import restaurantwebapp.model.Restaurant;
import restaurantwebapp.model.RestaurantType;
import restaurantwebapp.model.Meal;
import restaurantwebapp.service.RestaurantService;

import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RestaurantService restaurantService;

    @Autowired
    public DataInitializer(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public void run(String... args) throws Exception {

    }

    private Set<Restaurant> initializeData() {
        Set<Restaurant> restaurants = new HashSet<>();

        Restaurant restaurant1 = new Restaurant(UUID.randomUUID(), "Restaurant 1", "Address 1", RestaurantType.ASIAN);
        restaurant1.getMeals().add(new Meal(UUID.randomUUID(), "Meal 1", 10.0));
        restaurant1.getMeals().add(new Meal(UUID.randomUUID(), "Meal 2", 15.0));
        restaurants.add(restaurant1);

        Restaurant restaurant2 = new Restaurant(UUID.randomUUID(), "Restaurant 2", "Address 2", RestaurantType.ITALIAN);
        restaurant2.getMeals().add(new Meal(UUID.randomUUID(), "Meal 3", 12.0));
        restaurant2.getMeals().add(new Meal(UUID.randomUUID(), "Meal 4", 18.0));
        restaurants.add(restaurant2);

        Restaurant restaurant3 = new Restaurant(UUID.randomUUID(), "Restaurant 3", "Address 3", RestaurantType.AMERICAN);
        restaurant3.getMeals().add(new Meal(UUID.randomUUID(), "Meal 5", 9.0));
        restaurant3.getMeals().add(new Meal(UUID.randomUUID(), "Meal 6", 16.0));
        restaurants.add(restaurant3);

        for (Restaurant restaurant : restaurants) {
            restaurantService.addRestaurant(restaurant);
        }

        return restaurants;
    }
}
