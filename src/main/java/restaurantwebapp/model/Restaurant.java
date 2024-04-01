package restaurantwebapp.model;

import java.util.Set;
import java.util.UUID;

public class Restaurant {

    private final UUID id;
    private String name;
    private final String address;
    private final RestaurantType type;
    private Set<Meal> meals;

    public Restaurant(UUID id, String name, String address, RestaurantType type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
