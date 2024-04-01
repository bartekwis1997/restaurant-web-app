package restaurantwebapp.model;

import java.util.UUID;

public class Meal {

    private final UUID id;
    private final String name;
    private final double price;

    public Meal(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
