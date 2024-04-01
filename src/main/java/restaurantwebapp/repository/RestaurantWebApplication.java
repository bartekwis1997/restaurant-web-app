package restaurantwebapp.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RestaurantWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantWebApplication.class, args);
    }
}
