package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Address;
import org.upgrad.models.States;
import org.upgrad.services.AddressService;
import org.upgrad.services.StateService;
import org.upgrad.services.UserAuthTokenService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private StateService stateService;

    /*
     * This endpoint is used to insert the address of a user
     * Access token is used here for authentication purpose so that only logged in customer is able to add the address
     */
    @PostMapping("address")
    @CrossOrigin
    public ResponseEntity<?> addAddrss(@RequestParam String flatBuildingNumber,@RequestParam String locality, @RequestParam String city
                                        ,@RequestParam String zipcode,String type, @RequestParam int stateId,@RequestParam String accessToken)
    {
        String strType=null;
        if(type==null)
        {
             strType="temp";
        }
        else
        {
             strType="perm";
        }
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            int userID=userAuthTokenService.getUserId(accessToken);
           if(!addressService.validateZipAddress(zipcode))
           {
               return new ResponseEntity<>("Invalid zip code!",HttpStatus.UNAUTHORIZED);
           }
           else{
               addressService.addAddress(flatBuildingNumber,locality,city,zipcode,stateId);
               int address_id=addressService.getAddressId();
               addressService.addAddressType(strType,userID,address_id);
           }
        }

        return new ResponseEntity<>("Address has been saved successfully!",HttpStatus.OK);
    }

    /*
     * This endpoint is used to get the address detail of a user who have provided their permanent address.
     * Authentication is required to access this endpoint, so accessToken is taken as request header to make sure user is authenticated.
     */
    @GetMapping("/address/user")
    @CrossOrigin
    public ResponseEntity<?> getAllPermanentAddress(@RequestParam String accessToken) {
        List<Address> lstPermAddress = new ArrayList<>();
        if (userAuthTokenService.isUserLoggedIn(accessToken) == null) {
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if (userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt() != null) {
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else {
            int userID = userAuthTokenService.getUserId(accessToken);
            List<Integer> lstAddressId = addressService.getAddressId(userID);

            if (lstAddressId.size() < 1) {
                return new ResponseEntity<>("No permanent address found!", HttpStatus.NOT_FOUND);
            } else {
                for (int i = 0; i < lstAddressId.size(); i++) {
                    lstPermAddress.add(addressService.getAddressByID(lstAddressId.get(i)));
                }
            }

        }
        return new ResponseEntity<>(lstPermAddress,HttpStatus.OK);

    }
    /*
     * This endpoint is used get the address based on address id provided
     * Authentication is required to access this endpoint, so accessToken is taken as request header to make sure user is authenticated.
     */
    @PutMapping("/address/{addressId}")
    @CrossOrigin
    public ResponseEntity<?> updateAddress(String flatBuildingNumber, String locality,  String city
            ,String zipcode,  int stateId,@RequestParam int addressId,@RequestParam String accessToken)
    {
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            int userID=userAuthTokenService.getUserId(accessToken);
            if(!addressService.validateZipAddress(zipcode))
            {
                return new ResponseEntity<>("Invalid zip code!",HttpStatus.UNAUTHORIZED);
            }
            else{
                Boolean strIsAddressPresent=addressService.getAddress(addressId);
                if(strIsAddressPresent)
                {
                    addressService.updateAddress(flatBuildingNumber,locality,city,zipcode,stateId,addressId);
                }
                else
                {
                    return new ResponseEntity<>("No address with this address id!",HttpStatus.NOT_FOUND);
                }

            }
        }
        return new ResponseEntity<>("Address has been updated successfully!",HttpStatus.CREATED);

    }
    /*
     * This endpoint is used to delete a user based on their user id
     * Authentication is required to access this endpoint, so accessToken is taken as request header to make sure user is authenticated.
     */
    @DeleteMapping("/address/{addressId}")
    @CrossOrigin
    public ResponseEntity<?> deleteAddress(@RequestParam int addressId, @RequestParam String accessToken)
    {
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            Boolean strIsAddressPresent=addressService.getAddress(addressId);
            if(strIsAddressPresent)
            {
                addressService.deleteAddress(addressId);
            }
            else
            {
                return new ResponseEntity<>("No address with this address id!",HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Address has been deleted successfully!",HttpStatus.OK);
    }

    /*
     * This endpoint is used to get all the states.
     *
     */
    @GetMapping("states")
    @CrossOrigin
    public ResponseEntity<?> getAllStates()
    {
        List<States> lstStates=addressService.getAllStates();
        return new ResponseEntity<>(lstStates,HttpStatus.OK);
    }

}
