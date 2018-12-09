package org.upgrad.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.upgrad.models.Restaurant;
import org.upgrad.repositories.RestaurantRepository;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;

import java.util.List;

public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    private List<RestaurantResponse> restaurantResponseList;

    private List<Restaurant> restaurants;

    private RestaurantResponseCategorySet restaurantResponseCategorySet;

    @Override
    public List<RestaurantResponse> getAllRestaurant() {

        // to update
        //restaurants = restaurantRepository.findAllRestaurant();
        //this.restaurantResponseList = restaurants;
        return restaurantResponseList;
    }

    @Override
    public List<RestaurantResponse> getRestaurantByName(String restaurantName) {

        //restaurants = restaurantRepository.findRestaurantByName(restaurantName);
        //this.restaurantResponseList = restaurants;
        return restaurantResponseList;
    }

    @Override
    public List<RestaurantResponse> getRestaurantByCategory(String categoryName) {
        //restaurants = restaurantRepository.findRestaurantByCategory(categoryName);
        //this.restaurantResponseList = restaurants;
        return restaurantResponseList;
    }

    @Override
    public RestaurantResponseCategorySet getRestaurantDetails(int restaurantId) {
        return null;
    }

    @Override
    public Restaurant updateRating(int restaurantRating, int restaurantId) {

        Restaurant restaurant = new Restaurant ();
        //Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        //to implement
        return restaurant;
    }
}
