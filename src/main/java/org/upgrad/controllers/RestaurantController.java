package org.upgrad.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
package org.upgrad.model.Restaurant.java;
package org.upgrad.service.RestaurantService.java;

@Controller

@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService RestaurantService;

    @Autowired
    CategoryService CategoryService;



    @GetMapping("/api/restaurant")
    public ResponseEntity<?> getAllRestaurant(HttpSession session) {

        if (session.getAttribute("Restaurant")!==null) {
        return new ResponseEntity<>(RestaurantService.getAllRestaurant(RestaurantService.getRestaurantId ((String))), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/")
	public ModelAndView listRestaturant(ModelAndView model) throws IOException {
		List<Restaurant> listRestaturant = RestaurantService.getAllRestaurant();
		model.addObject("listRestaturant", listRestaturant);
		//model.setViewName("home");
		return model;
    }
    @GetMapping("/api/restaurant/name/{restaurantName}")
    public ResponseEntity<?> getRestaurantByName(@RequestParam("RestaurantName") int restaurantName, HttpSession session) {

        if (session.getAttribute("restaurantName")==null) {
            return new ResponseEntity<>("No Restaurant under this name!", HttpStatus.NOTFOUND);
        }

        else {

            return new ResponseEntity<>(RestaurantService.getRestaurantByName(restaurantName), HttpStatus.OK);
        }
    }

    @GetMapping("/api/restaurant/category/{categoryName}")
    public ResponseEntity<?> getAllRestaurantByCategory(@RequestParam("categoryName") String categoryName, HttpSession session) {

        if (session.getAttribute("catergoryname")==null) {
            return new ResponseEntity<>("No Restaurant under this category!", HttpStatus.NOTFOUND);
        }

        else {

            return new ResponseEntity<>(RestaurantService.getAllRestaurantByCategory(categoryName), HttpStatus.OK);
        }
    }
    @GetMapping("/api/restaurant/{restaurantId}")
    public ResponseEntity<?> getRestaurantByRestaurantId(@RequestParam("restaurantId") int restaurantId,HttpSession session) {

            if(RestaurantService.checkRestaurantEntry (restaurantId) > 0){
                return new ResponseEntity<>(RestaurantService.getRestaurantByRestaurantId(restaurantId), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("No Restaurant by this id! " + restaurantId, HttpStatus.NOT_FOUND);
            }
        }
        @PutMapping("/api/restaurant/{restaurantId}")
        public ResponseEntity<?> updateRestaurantDetails(@RequestParam("Details") String DetailsBody,@RequestParam("restaurantId") int restaurantId,HttpSession session) {
    
    
    
    
            if (session.getAttribute("currUser")==null) {
                return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
            }
    
            else {
    
                if(RestaurantService.checkDetailsEntry (restaurantId) > 0){
                    String userRole = userService.getCurrentUserRole((String) session.getAttribute("currUser"));
                    int userId = RestaurantService.findRestaurant(restaurantId);
    
                    if(userId == (userService.getUserID ((String) session.getAttribute("currUser"))) || userRole.equalsIgnoreCase ("admin")){
    
                        restaurantService.updateRestaurantDetails (restaurantId,DetailsBody);
                        return new ResponseEntity<>("update with restaurantId "+restaurantId + " edited successfully", HttpStatus.OK);
                    }
    
                    else{
                        return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint! ", HttpStatus.UNAUTHORIZED);
                    }
                }
                else{
                        return new ResponseEntity<>("No Restaurant by this id! " + restaurantId, HttpStatus.NOT_FOUND);
                    }
    
            }
    
    }


}
