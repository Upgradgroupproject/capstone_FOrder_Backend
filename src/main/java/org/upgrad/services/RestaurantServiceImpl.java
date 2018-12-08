package org.upgrad.services;

public class RestaurantServiceImpl{

    @Override
    public List<Restaurant> getAllRestaurant() {
        return RestaurantRepository.getAllRestaurant();
    }
    @Override
    public Restaurant getRestaurantByName(String RestaurantName) {
        return RestaurantRepository.getRestaurantByName (RestaurantName);
    }
    @Override
    public List<Restaurant> getAllRestaurantByCategory(String categoryName) {
        return RestaurantRepository.getAllRestaurantByCategory(categoryName);
    }

    @Override
    public Restaurant getRestaurantByRestaurantId(int restaurantId) {
        return RestaurantRepository.getRestaurantByRestaurantId (restaurantId);
    }
    @Override
    public int checkDetailsEntry(int restaurantId) {

        Long restaurantId = RestaurantRepository.checkDetailsEntry (restaurantId);

        if (restaurantId == null) {
            return 0;
        } else
            return restaurantId.intValue ();


    }
}
