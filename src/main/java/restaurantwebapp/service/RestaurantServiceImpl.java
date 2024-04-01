package restaurantwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurantwebapp.model.Meal;
import restaurantwebapp.model.Restaurant;
import restaurantwebapp.repository.RestaurantRepository;

import java.util.*;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
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
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return new HashSet<>(restaurantList);
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant deleteRestaurantById(UUID id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            restaurantRepository.deleteById(id);
            return restaurant;
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    @Override
    public Meal addMealToRestaurant(UUID restaurantId, Meal meal) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        restaurant.getMeals().add(meal);
        restaurantRepository.save(restaurant);
        return meal;
    }

    @Override
    public Meal deleteMealFromRestaurant(UUID restaurantId, UUID mealId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Optional<Meal> mealOptional = restaurant.getMeals().stream()
                .filter(meal -> meal.getId().equals(mealId))
                .findFirst();

        if (mealOptional.isPresent()) {
            Meal deletedMeal = mealOptional.get();
            restaurant.getMeals().remove(deletedMeal);
            restaurantRepository.save(restaurant);
            return deletedMeal;
        } else {
            throw new RuntimeException("Meal not found in the restaurant");
        }
    }


}
