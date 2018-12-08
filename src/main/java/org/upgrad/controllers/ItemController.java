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
package org.upgrad.model.Item;
package org.upgrad.service.ItemService;



@RestController
@RequestMapping("/api/item/restaurant/{restaurantId}")
public class ItemController {
    @Autowired
    ItemService ItemService;
    @GetMapping("/api/item/restaurant/{restaurantId}")
    public ResponseEntity<?> getRestaurantByRestaurantId(@RequestParam("RestaurantId") int RestaurantId, HttpSession session) {

        if (session.getAttribute("Restaurant")==null) {
            return new ResponseEntity<>("No Restaurant by this id!", HttpStatus.NOCONTENT);
        }

        else {

            return new ResponseEntity<>(RestaurantService.getRestaurantByRestaurantId(RestaurantId), HttpStatus.OK);
        }
    }
	@RequestMapping(value = "/")
	public ModelAndView listitem(ModelAndView model) throws IOException {
		List<Item> listItem = ItemService.getAllItem(4);
		model.addObject("listItem", listItem);
		model.setViewName("");
		return model;
	}
}
