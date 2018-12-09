package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Coupon;
import org.upgrad.models.Order;
import org.upgrad.requestResponseEntity.ItemQuantity;
import org.upgrad.services.AddressService;
import org.upgrad.services.OrderService;
import org.upgrad.services.UserAuthTokenService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    private AddressService addressService;

    @GetMapping("")
    public ResponseEntity<?> getOrdersByUser(@RequestHeader("accessToken") String accessToken) {

        if (userAuthTokenService.isUserLoggedIn(accessToken) == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {

            Integer userId = userAuthTokenService.getUserId(accessToken);
            List<Order> orderPlaced = orderService.getOrdersByUser(userId);
            if (orderPlaced.size ()==0) {
                return new ResponseEntity<>("No orders have been made yet!", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(orderPlaced, HttpStatus.OK);
            }
        }

    }

    @GetMapping("/coupon/{couponName}")
    public ResponseEntity<?> getCouponByName(@PathVariable("couponName") String couponName,
                                             @RequestHeader("accessToken") String accessToken) {
        if (userAuthTokenService.isUserLoggedIn(accessToken) == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            Coupon couponApplied = orderService.getCoupon(couponName);
            if (couponApplied == null) {
                return new ResponseEntity<>("Invalid Coupon!", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(couponApplied, HttpStatus.OK);
            }

        }
    }

    @PostMapping("")
    public ResponseEntity<?> addOrder(@RequestParam("addressId") Integer addressId,
                                      String flatBuilNo, String locality, String city, String zipcode, Integer stateId,
                                      String type, @RequestParam("paymentId") Integer paymentId,
                                      @RequestBody ArrayList<ItemQuantity> itemQuantities, @RequestParam("bill") Double bill,
                                      Integer couponId, @RequestParam("discount") Double discount,
                                      @RequestHeader("accessToken") String accessToken) {

        if (userAuthTokenService.isUserLoggedIn(accessToken) == null) {

            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);

        } else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {

            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!",
                    HttpStatus.UNAUTHORIZED);

        } else {

            Integer placedOrderId; // to return
            Integer userId = userAuthTokenService.getUserId(accessToken);

            if (addressId ==null) {

                if (zipcode == null || addressService.validateZipAddress (zipcode)) {

                    return new ResponseEntity<>("Invalid zip code!", HttpStatus.BAD_REQUEST);
                }
                placedOrderId = orderService.addOrder(flatBuilNo, locality, city, zipcode, stateId, type,
                        paymentId, userId, itemQuantities, bill, couponId, discount);

                return new ResponseEntity<>(placedOrderId, HttpStatus.OK);

            } else {

                placedOrderId = orderService.addOrderWithPermAddress(addressId, paymentId, userId,
                        itemQuantities, bill, couponId, discount);
                return new ResponseEntity<>(placedOrderId, HttpStatus.OK);
            }

        }
    }



}