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
    /*
     * This endpoint is used to signup a new user.
     * here we are checking if the number is already used or not if it is already used,Appropriate error message is thrown
     * If no is not used we are validating the password and zipcode and then inserting it into DB
     */
    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity<?> signup(@RequestParam String firstName, String lastName,@RequestParam String emailField,@RequestParam
            String contactNumber,@RequestParam String password)
    {
        String contactNumberExists=userService.getContactNumber(contactNumber);
        Boolean boolEmailByUser=userService.matchExpression("email",emailField);
        if(contactNumberExists!=null)
        {
            return new ResponseEntity<>("Try any other contact number ,this contact number has already been used!! ", HttpStatus.UNAUTHORIZED);
        }
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
        return new ResponseEntity<>("User with contact number "+contactNumber+" created",HttpStatus.CREATED);
    }

    /*
     * This endpoint is used to update the first name and last name of the logged in user
     * Access token we are using here to check if the user is logged in . If he is logged in the appropriate action is performed
     */
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
            int userID=userAuthTokenService.getUserId(accessToken);
            userService.updateUser(firstName,lastName,userID);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user = userService.getUserById(userID);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
    }
    /*
     * This endpoint is used to update the password of a user
     * Access token is used to make sure only authenticated user is allowed to do this operation
     */
    @PutMapping("/password")
    @CrossOrigin
    public ResponseEntity<?> updatePassword(@RequestParam String oldPassword,@RequestParam String newPassword,@RequestParam String accessToken )
    {
        int userID=0;
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }

        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
             userID=userAuthTokenService.getUserId(accessToken);
            String oldpasswordByUser=userService.getUserPassword(userID).toLowerCase().toString();
            String encryptedpasword= Hashing.sha256()
                    .hashString(oldPassword, Charsets.US_ASCII)
                    .toString();
            if(!oldpasswordByUser.equals(encryptedpasword))
            {
                return new ResponseEntity<>("Your password did not match to your old password!",HttpStatus.UNAUTHORIZED);
            }
            else if(userService.matchExpression("password",newPassword))
            {
                String encryptedNewpasword= Hashing.sha256()
                        .hashString(newPassword, Charsets.US_ASCII)
                        .toString();
                userService.updatePassword(encryptedNewpasword,userID);
            }
            else
            {
                return new ResponseEntity<>("Weak password!",HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>("Password updated successfully",HttpStatus.OK);

    }

}
