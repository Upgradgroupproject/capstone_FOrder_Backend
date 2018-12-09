package org.upgrad.repositories;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Address;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address,Integer>{

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT into address(flat_buil_number,locality,city,zipcode,state_id) values(?1,?2,?3,?4,?5)")
    void addAddress(String flatBuiltNumber, String locality, String city, String zipcode, int state_id);

    @Query(nativeQuery = true,value = "SELECT id from address order by id desc limit 1")
    int getAddressId();

    @Query(nativeQuery = true,value = "Select * from address where id=(?1)")
    Address getAddress(int addressId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update address set flat_buil_number=(?1),locality=(?2),city=(?3),zipcode=(?4),state_id=(?5) where id=(?6) ")
    void updateAddress(String flatBuiltNumber, String locality, String city, String zipcode, int state_id,int addressId);

    @Query(nativeQuery = true, value = "SELECT id FROM address ORDER BY id DESC limit 1")
    Integer findLastAddedAddress();

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from address where id=(?1)")
    void deleteAddress(int addressId);


}
