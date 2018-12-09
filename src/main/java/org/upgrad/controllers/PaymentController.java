package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Payment;
import org.upgrad.services.PaymentService;
import org.upgrad.services.UserAuthTokenService;

import java.util.List;

/*
 * This endpoint is used to check available Payment Options
 * Access token is used here for authentication purpose so that only logged in customer is able to check payment options
 *
 */


@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    @Autowired
    private PaymentService paymentService;


    @GetMapping("")
    public ResponseEntity<?> paymentMethod(@RequestHeader String accessToken) {

        if (userAuthTokenService.isUserLoggedIn(accessToken) == null) {

            return new ResponseEntity<>("Please Login first to access this endpoint!",
                    HttpStatus.UNAUTHORIZED);
        }
          else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {

            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!",
                    HttpStatus.UNAUTHORIZED);
        }
          else {
            List<Payment> payments = paymentService.getPaymentMethods();
            return new ResponseEntity<>(payments, HttpStatus.OK);
        }
    }




}
