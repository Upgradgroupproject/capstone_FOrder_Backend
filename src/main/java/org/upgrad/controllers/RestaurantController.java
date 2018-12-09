package org.upgrad.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Restaurant;
import org.upgrad.models.UserAuthToken;
import org.upgrad.requestResponseEntity.RestaurantResponse;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;
import org.upgrad.services.CategoryService;
import org.upgrad.services.RestaurantService;
import org.upgrad.services.UserAuthTokenService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    private RestaurantResponseCategorySet restaurantResponseCategorySet;

    private Restaurant restaurant;

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    @GetMapping("")
    public ResponseEntity<?> getAllRestaurant() {

        return new ResponseEntity<> (restaurantService.getAllRestaurant (), HttpStatus.OK);

    }

    @GetMapping("/name/{restaurantName}")
    public ResponseEntity<?> getRestaurantByName(@PathVariable("restaurantName") String restaurantName) {

        if (("restaurantName") == null) {
            return new ResponseEntity<> ("No Restaurant by this name!", HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<> (restaurantService.getRestaurantByName (restaurantName), HttpStatus.OK);
        }
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<?> getAllRestaurantByCategory(@PathVariable("categoryName") String categoryName) {

        if (("catergoryname") == null) {
            return new ResponseEntity<> ("No Restaurant under this category!", HttpStatus.NOT_FOUND);
        } else {

            List<RestaurantResponse> restaurantResponse = restaurantService.getRestaurantByCategory(categoryName);

            if (restaurantResponse == null || restaurantResponse.size () == 0) {
                return new ResponseEntity<> ("No Restaurant by this name!", HttpStatus.NOT_FOUND);
            } else {

                return new ResponseEntity<> (restaurantService.getRestaurantByCategory(categoryName), HttpStatus.OK);
            }
        }
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<?> getRestaurantByRestaurantId(@PathVariable("restaurantId") int restaurantId) {

        if (restaurantService.getRestaurantById (restaurantId) == null) {

            return new ResponseEntity<> ("No Restaurant by this id! " + restaurantId, HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<> (restaurantService.getRestaurantById (restaurantId), HttpStatus.OK);
        }
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> updateRestaurantDetails(@RequestParam("rating") String rating, @PathVariable("restaurantId") int restaurantId,
                                                     @RequestHeader String accessToken) {


        if (userAuthTokenService.isUserLoggedIn (accessToken) == null) {

            return new ResponseEntity<> ("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);

        } else if (userAuthTokenService.isUserLoggedIn (accessToken).getLogoutAt () != null) {

            return new ResponseEntity<> ("You have already logged out. Please Login first to access this endpoint!",
                    HttpStatus.UNAUTHORIZED);

        } else {

            RestaurantResponseCategorySet restaurantResponseCategorySet = restaurantService.getRestaurantDetails (restaurantId);

            if (restaurantResponseCategorySet == null) {

                return new ResponseEntity<> ("No Restaurant by this id!", HttpStatus.NOT_FOUND);

            } else {

                Restaurant restaurant = restaurantService.updateRating (Integer.parseInt (rating), restaurantId);
                return new ResponseEntity<> (restaurant, HttpStatus.OK);
            }
        }


    }
}


