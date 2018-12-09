package org.upgrad.services;

public interface AddressService {

    public boolean validateZipAddress(String zipAddress);
    public void addAddress(String flatBuiltNumber,String locality,String city, String zipcode,int state_id);
    public int getAddressId();
    public void addAddressType(String type, int user_id, int address_id);

}
