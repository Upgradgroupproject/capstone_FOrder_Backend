package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Coupon;
import org.upgrad.services.OrderService;
import org.upgrad.services.UserAuthTokenService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    @GetMapping("coupon/{couponName}")
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

    @GetMapping("")
    public ResponseEntity<?> getOrdersByUser(@RequestHeader("accessToken") String accessToken) {

        if (userAuthTokenService.isUserLoggedIn(accessToken) == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {

                return new ResponseEntity<>("in progress", HttpStatus.OK);
            }

    }



}
