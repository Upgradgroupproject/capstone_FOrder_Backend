package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.models.Item;
import org.upgrad.requestResponseEntity.RestaurantResponseCategorySet;
import org.upgrad.services.ItemService;
import org.upgrad.services.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    RestaurantService restaurantService;


    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> getItemByRestaurantId(@PathVariable("RestaurantId") int restaurantId) {

        RestaurantResponseCategorySet restaurantInfo = restaurantService.getRestaurantDetails(restaurantId);

        if (restaurantInfo == null) {
            return new ResponseEntity<>("No Restaurant by this id!", HttpStatus.BAD_REQUEST);

        } else {

            List<Item> popularItems = itemService.getTop5ItemsByPopularity(restaurantId);
            return new ResponseEntity<>(popularItems, HttpStatus.OK);
        }
    }

}
