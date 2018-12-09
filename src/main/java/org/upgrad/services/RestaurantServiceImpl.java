package org.upgrad.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
import org.upgrad.models.Restaurant;
import org.upgrad.repositories.RestaurantRepository;
import org.upgrad.requestResponseEntity.CategoryResponse;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;

import java.util.*;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    private List<RestaurantResponse> restaurantResponseList;

    private List<Restaurant> restaurants;

    private Restaurant restaurant;

    private RestaurantResponseCategorySet restaurantResponseCategorySet;

    @Override
    public List<RestaurantResponse> getAllRestaurant() {

        // to update
        restaurants = restaurantRepository.getAllRestaurant ();
        //this.getRestaurantResponseList(restaurants);
        return restaurantResponseList;
    }

    @Override
    public List<RestaurantResponse> getRestaurantByName(String restaurantName) {

        restaurant = restaurantRepository.getRestaurantByName(restaurantName);
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
    public Restaurant getRestaurantById(int restaurantId){

        Restaurant restaurant = restaurantRepository.getRestaurantByRestaurantId (restaurantId);
        return restaurant;
    }

    @Override
    public RestaurantResponseCategorySet getRestaurantDetails(int id) {
        Restaurant restaurant = restaurantRepository.getRestaurantByRestaurantId (id);

        if (restaurant != null) {

            Set<CategoryResponse> categorySet = new HashSet<> ();
            List<Category> categories = restaurant.getRestaurantCategories ();

            for (Category category:categories) {

                CategoryResponse categoryResponse = new CategoryResponse(category.getId(), category.getCategoryName(), category.getItems());
                categorySet.add(categoryResponse);

            }

            restaurantResponseCategorySet = new RestaurantResponseCategorySet(restaurant.getId(), restaurant.getRestaurantName(),
                    restaurant.getPhotoUrl(), restaurant.getUserRating(), restaurant.getAvgPrice(), restaurant.getNumberUsersRated(),
                    restaurant.getAddress(), categorySet);
        }
        return restaurantResponseCategorySet;
    }

    @Override
    public Restaurant updateRating(int restaurantRating, int restaurantId) {

        Restaurant restaurant = restaurantRepository.getRestaurantByRestaurantId (restaurantId);

        restaurantRepository.updateRating(restaurantRating,restaurantId , restaurant.getNumberUsersRated()+1);

        return restaurantRepository.getRestaurantByRestaurantId (restaurantId);

    }
}