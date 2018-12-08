package org.upgrad.controllers;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.User;
import org.upgrad.services.UserAuthTokenService;
import org.upgrad.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    /*
     * This endpoint is used to login a user.
     * Here contact number and password has to be provided to match the credentials.
     */
    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<?> login(@RequestParam String contactNumber, @RequestParam String password){
        String passwordByUser = String.valueOf(userService.findUserPassword(contactNumber));
        String sha256hex = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();
        if(userService.findUserPassword(contactNumber)==null) return new ResponseEntity<>("This contact number has not been registered!",HttpStatus.OK);
        else if (!(passwordByUser.equalsIgnoreCase(sha256hex))) {
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
        }
        else{
            User user = userService.findUser(contactNumber);
            String accessToken = UUID.randomUUID().toString();
            userAuthTokenService.addAccessToken(user.getId(),accessToken);
            HttpHeaders headers = new HttpHeaders();
            headers.add("access-token", accessToken);
            List<String> header = new ArrayList<>();
            header.add("access-token");
            headers.setAccessControlExposeHeaders(header);
            return new ResponseEntity<>(user,headers,HttpStatus.OK);
        }
    }

    /*
     * This endpoint is used to logout a user.
     * Authentication is required to access this endpoint, so accessToken is taken as request header to make sure user is authenticated.
     */
    @PutMapping("/logout")
    @CrossOrigin
    public ResponseEntity<String> logout(@RequestHeader String accessToken){
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            userAuthTokenService.removeAccessToken(accessToken);
            return new ResponseEntity<>("You have logged out successfully!",HttpStatus.OK);}
    }
    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity<?> signup(@RequestParam String firstName, String lastName,@RequestParam String emailField,@RequestParam
            String contactNumber,@RequestParam String password)
    {
        String contactNumberExists=String.valueOf(userService.getContactNumber(contactNumber)).toString();
        Boolean boolEmailByUser=userService.matchExpression("email",emailField);
      /*  if(contactNumberExists!=null)
        {
            return new ResponseEntity<>("Try any other contact number ,this contact number has already been used!! ", HttpStatus.UNAUTHORIZED);
        }*/
        if(!userService.matchExpression("contact",contactNumber))
        {
            return new ResponseEntity<>("Invalid contact Number! ", HttpStatus.UNAUTHORIZED);
        }
        else if(!boolEmailByUser)
        {
            return new ResponseEntity<>("Invalid Email!!", HttpStatus.UNAUTHORIZED);
        }
        else if(!userService.matchExpression("password",password))
        {
            return new ResponseEntity<>("Invalid Password!!", HttpStatus.UNAUTHORIZED);
        }
        else
        {
            String sha256hex = Hashing.sha256()
                    .hashString(password, Charsets.US_ASCII)
                    .toString();
            userService.createUsers(firstName,lastName,emailField,contactNumber,sha256hex);
        }
        return new ResponseEntity<>("User with contact number "+contactNumber+" create",HttpStatus.CREATED);
    }

    @PutMapping("/user")
    @CrossOrigin
    public ResponseEntity<?> update(@RequestParam String firstName,@RequestParam String lastName, @RequestParam String accessToken)
    {
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            int userID=userAuthTokenService.getUserID(accessToken);
            userService.updateUser(firstName,lastName,userID);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = userService.findByUserID(userID);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
    }

}
