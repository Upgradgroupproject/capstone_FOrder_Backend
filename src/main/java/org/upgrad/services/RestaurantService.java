package org.upgrad.services;

public interface RestaurantService {

    List<Restaurant> getAllRestaurant();
    Restaurant getRestaurantByName(RestaurantName);  
    List<Restaurant> getAllRestaurantByCategory(String categoryName);
    Restaurant getRestaurantByRestaurantId(int restaurantId);
    void updateRestaurantDetails(int restaurantId ,String DetailsBody);
}

