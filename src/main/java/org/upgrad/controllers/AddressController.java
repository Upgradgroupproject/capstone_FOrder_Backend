package org.upgrad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.upgrad.services.AddressService;
import org.upgrad.services.UserAuthTokenService;

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
        if(type==null)
        {
            String strType="temp";
        }
        else
        {
            String strType="perm";
        }
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            int userID=userAuthTokenService.getUserID(accessToken);
           if(!addressService.validateZipAddress(zipcode))
           {
               return new ResponseEntity<>("Invalid zip code!",HttpStatus.UNAUTHORIZED);
           }
           else{
               addressService.addAddress(flatBuildingNumber,locality,city,zipcode,stateId);
               int address_id=addressService.getAddressId();
               addressService.addAddressType(type,userID,address_id);
           }
        }

        return new ResponseEntity<>("Address has been saved successfully!",HttpStatus.OK);
    }

}
