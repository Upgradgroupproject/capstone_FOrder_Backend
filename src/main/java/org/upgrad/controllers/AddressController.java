package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.models.Address;
import org.upgrad.services.AddressService;
import org.upgrad.services.UserAuthTokenService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    @Autowired
    private AddressService addressService;

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
                    lstPermAddress.add(addressService.getAddress(lstAddressId.get(i)));
                }
            }

        }
        return new ResponseEntity<>(lstPermAddress,HttpStatus.OK);

    }
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
                Address strIsAddressPresent=addressService.getAddress(addressId);
                if(strIsAddressPresent!=null)
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

}
