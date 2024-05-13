package com.example.restaurantwebapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.restaurantwebapp.model.Meal;
import com.example.restaurantwebapp.model.Restaurant;
import com.example.restaurantwebapp.service.RestaurantService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    void should_get_restaurant_by_id() throws Exception {
        // Given
        UUID restaurantId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant", "Test Address", null);

        // When
        Mockito.when(restaurantService.getRestaurantById(restaurantId)).thenReturn(restaurant);

        // Then
        mockMvc.perform(get("/restaurants/{id}", restaurantId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(restaurantId.toString()))
                .andExpect(jsonPath("$.name").value("Test Restaurant"))
                .andExpect(jsonPath("$.address").value("Test Address"));
    }

    @Test
    void should_get_all_restaurants() throws Exception {
        // Given
        UUID restaurantId1 = UUID.fromString("b295982e-ee00-43f3-a1c5-2f8e4e7f348e");
        UUID restaurantId2 = UUID.fromString("f04aade9-ec2b-43b5-a96f-804f4c2a367e");
        Set<Restaurant> restaurants = new HashSet<>();
        restaurants.add(new Restaurant(restaurantId1, "Restaurant 1", "Address 1", null));
        restaurants.add(new Restaurant(restaurantId2, "Restaurant 2", "Address 2", null));

        // When
        Mockito.when(restaurantService.getAllRestaurants()).thenReturn(restaurants);

        // Then
        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(restaurantId1.toString()))
                .andExpect(jsonPath("$[0].name").value("Restaurant 1"))
                .andExpect(jsonPath("$[0].address").value("Address 1"))
                .andExpect(jsonPath("$[1].id").value(restaurantId2.toString()))
                .andExpect(jsonPath("$[1].name").value("Restaurant 2"))
                .andExpect(jsonPath("$[1].address").value("Address 2"));
    }

    @Test
    void should_add_restaurant() throws Exception {
        // Given
        UUID restaurantId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant", "Test Address", null);

        // When
        Mockito.when(restaurantService.addRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        // Then
        mockMvc.perform(post("/addrestaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(restaurantId.toString()))
                .andExpect(jsonPath("$.name").value("Test Restaurant"))
                .andExpect(jsonPath("$.address").value("Test Address"));
    }

    @Test
    void should_delete_restaurant_by_id() throws Exception {
        // Given
        UUID restaurantId = UUID.randomUUID();
        Restaurant restaurant = new Restaurant(restaurantId, "Test Restaurant", "Test Address", null);

        // When
        Mockito.when(restaurantService.deleteRestaurantById(restaurantId)).thenReturn(restaurant);

        // Then
        mockMvc.perform(delete("/restaurants/{id}", restaurantId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(restaurantId.toString()))
                .andExpect(jsonPath("$.name").value("Test Restaurant"))
                .andExpect(jsonPath("$.address").value("Test Address"));
    }

    @Test
    void should_add_meal_to_restaurant() throws Exception {
        // Given
        UUID restaurantId = UUID.randomUUID();
        UUID mealId = UUID.randomUUID();
        Meal meal = new Meal("Test Meal", 10.0);
        meal.setId(mealId);

        // When
        Mockito.when(restaurantService.addMealToRestaurant(eq(restaurantId), any(Meal.class))).thenReturn(meal);

        // Then
        mockMvc.perform(post("/restaurants/{restaurantId}/meals", restaurantId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meal)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mealId.toString()))
                .andExpect(jsonPath("$.name").value("Test Meal"))
                .andExpect(jsonPath("$.price").value(10.0));
    }

    @Test
    void should_delete_meal_from_restaurant() throws Exception {
        // Given
        UUID restaurantId = UUID.randomUUID();
        UUID mealId = UUID.randomUUID();
        Meal meal = new Meal("Test Meal", 10.0);
        meal.setId(mealId);

        // When
        Mockito.when(restaurantService.deleteMealFromRestaurant(eq(restaurantId), eq(mealId))).thenReturn(meal);

        // Then
        mockMvc.perform(delete("/restaurants/{restaurantId}/meals/{mealId}", restaurantId, mealId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mealId.toString()))
                .andExpect(jsonPath("$.name").value("Test Meal"))
                .andExpect(jsonPath("$.price").value(10.0));
    }

}