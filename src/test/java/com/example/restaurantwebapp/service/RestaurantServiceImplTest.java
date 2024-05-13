package com.example.restaurantwebapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.restaurantwebapp.model.Meal;
import com.example.restaurantwebapp.model.Restaurant;
import com.example.restaurantwebapp.model.RestaurantType;
import com.example.restaurantwebapp.repository.RestaurantRepository;

import java.util.*;

@SpringBootTest
class RestaurantServiceImplTest {

    private RestaurantServiceImpl restaurantService;
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        restaurantService = new RestaurantServiceImpl(restaurantRepository);
        restaurantRepository = mock(RestaurantRepository.class);
    }

    @Test
    void should_get_restaurant_by_id() {
        //Given
        UUID restaurantId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant(restaurantId, "Test name", "Test Address", null);
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        //When
        Restaurant result = restaurantService.getRestaurantById(restaurantId);

        //Then
        assertNotNull(result);
        assertEquals(restaurantId, result.getId());
    }

    @Test
    void should_get_all_restaurants() {
        //Given
        Set<Restaurant> restaurants = new HashSet<>();
        restaurants.add(new Restaurant(UUID.randomUUID(), "Restaurant 1", "Address 1", null));
        restaurants.add(new Restaurant(UUID.randomUUID(), "Restaurant 2", "Address 2", null));
        when(restaurantRepository.findAll()).thenReturn(restaurants);

        //When
        Set<Restaurant> result = restaurantService.getAllRestaurants();

        //Then
        assertEquals(new HashSet<>(restaurants), result);
    }

    @Test
    void should_add_restaurant() {
        //Given
        UUID restaurantId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant", "Test Address", null);
        when(restaurantRepository.saveRestaurant(restaurant)).thenReturn(restaurant);

        //When
        Restaurant result = restaurantService.addRestaurant(restaurant);

        //Then
        assertNotNull(result);
        assertEquals(restaurantId, result.getId());
        assertEquals("Test Restaurant", result.getName());
        assertEquals("Test Address", result.getAddress());
    }

    @Test
    void should_delete_restaurant_by_given_id() {
        //Given
        UUID restaurantId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant", "Test Address", null);
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        //When
        Restaurant result = restaurantService.deleteRestaurantById(restaurantId);

        //Then
        assertNotNull(result);
        assertEquals(restaurantId, result.getId());
        assertEquals("Test Restaurant", result.getName());
        assertEquals("Test Address", result.getAddress());
        verify(restaurantRepository, times(1)).deleteById(restaurantId);
    }

    @Test
    void should_add_meal_to_restaurant() {
        //Given
        UUID restaurantId = UUID.randomUUID();
        Meal meal = new Meal("Test Meal", 10.0);
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant", "Test Address", RestaurantType.FRENCH);
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        //When
        Meal result = restaurantService.addMealToRestaurant(restaurantId, meal);

        //Then
        assertNotNull(result);
        assertEquals("Test Meal", result.getName());
        assertEquals(10.0, result.getPrice());
        assertEquals(1, restaurant.getMeals().size());
        assertTrue(restaurant.getMeals().contains(result));
    }

    @Test
    void should_delete_meal_from_restaurant() {
        //Given
        UUID restaurantId = UUID.randomUUID();
        UUID mealId = UUID.randomUUID();
        Meal meal = new Meal("Test Meal", 10.0);
        meal.setId(mealId);
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant", "Test Address", RestaurantType.AMERICAN);
        restaurant.getMeals().add(meal);
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));

        //When
        Meal result = restaurantService.deleteMealFromRestaurant(restaurantId, mealId);

        //Then
        assertNotNull(result);
        assertEquals(mealId, result.getId());
        assertEquals("Test Meal", result.getName());
        assertEquals(10.0, result.getPrice());
        assertEquals(0, restaurant.getMeals().size());
        assertFalse(restaurant.getMeals().contains(result));
    }
}