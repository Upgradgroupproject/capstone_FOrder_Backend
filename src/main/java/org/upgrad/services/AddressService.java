package org.upgrad.services;

import org.upgrad.models.Address;

import java.util.List;

public interface AddressService {

    public boolean validateZipAddress(String zipAddress);
    public void addAddress(String flatBuiltNumber,String locality,String city, String zipcode,int state_id);
    public int getAddressId();
    public void addAddressType(String type, int user_id, int address_id);
    public List<Integer> getAddressId(int userId);
    Address getAddress(int addressId);

}
