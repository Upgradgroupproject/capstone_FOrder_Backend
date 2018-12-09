package org.upgrad.services;

import org.upgrad.models.Address;
import org.upgrad.models.States;

import java.util.List;

public interface AddressService {

    public boolean validateZipAddress(String zipAddress);
    public void addAddress(String flatBuiltNumber,String locality,String city, String zipcode,int state_id);
    public int getAddressId();
    public void addAddressType(String type, int user_id, int address_id);
    public List<Integer> getAddressId(int userId);
    Boolean getAddress(int addressId);
    void updateAddress(String flatBuiltNumber, String locality, String city, String zipcode, int state_id,int addressId);
    public void deleteAddress(int addressId);
    Address getAddressByID(int addressId);
    List<Address> getPermAddress(int userId);
    List<States> getAllStates();

}
