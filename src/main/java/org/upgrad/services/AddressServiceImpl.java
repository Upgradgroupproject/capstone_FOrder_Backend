package org.upgrad.services;


import org.springframework.stereotype.Service;
import org.upgrad.models.Address;
import org.upgrad.repositories.AddressRepository;
import org.upgrad.repositories.UserAddressRepository;
import org.upgrad.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class AddressServiceImpl implements AddressService{
    private final AddressRepository addressRepository;
    public final UserAddressRepository userAddressRepository;

    public AddressServiceImpl(AddressRepository addressRepository,UserAddressRepository userAddressRepository) {
        this.addressRepository = addressRepository;
        this.userAddressRepository=userAddressRepository;
    }

    @Override
    public boolean validateZipAddress(String zipAddress) {
        Pattern pattern = Pattern.compile("\\d{6}");
        Matcher matcher=pattern.matcher(zipAddress);
        return matcher.matches();
    }




    @Override
    public void addAddress(String flatBuiltNumber, String locality, String city, String zipcode, int state_id) {
        addressRepository.addAddress(flatBuiltNumber,locality,city,zipcode,state_id);
    }

    @Override
    public int getAddressId() {
        return addressRepository.getAddressId();
    }

    @Override
    public void addAddressType(String type, int user_id, int address_id) {
        userAddressRepository.addAddressType(type,  user_id,  address_id);
    }

    @Override
    public List<Integer> getAddressId(int userId) {
        return userAddressRepository.getAddressId(userId);
    }

    @Override
    public Address getAddress(int addressId) {
        return addressRepository.getAddress(addressId);
    }

    @Override
    public void updateAddress(String flatBuiltNumber, String locality, String city, String zipcode, int state_id,int addressId) {
        addressRepository.updateAddress(flatBuiltNumber,locality,city,zipcode,state_id, addressId);
    }

}
