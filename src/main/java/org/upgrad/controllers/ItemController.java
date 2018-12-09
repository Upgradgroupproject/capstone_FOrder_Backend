package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.models.Item;
import org.upgrad.models.Restaurant;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;
import org.upgrad.services.ItemService;
import org.upgrad.services.RestaurantService;

import java.util.List;


/*
 * This endpoint is to check popular item by a restaurant
 *     restaurant Id is needed
 */

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    RestaurantService restaurantService;


    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> getItemByRestaurantId(@PathVariable("restaurantId") int restaurantId) {

        Restaurant restaurantInfo = restaurantService.getRestaurantById (restaurantId);

        if (restaurantInfo == null) {

            return new ResponseEntity<>("No Restaurant by this id!", HttpStatus.BAD_REQUEST);

        } else {

            List<Item> popularItems = itemService.getTop5ItemsByPopularity(restaurantId);
            return new ResponseEntity<>(popularItems, HttpStatus.OK);
        }
    }

}
