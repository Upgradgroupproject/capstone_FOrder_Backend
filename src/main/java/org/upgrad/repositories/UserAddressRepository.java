package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Address;
import org.upgrad.models.UserAddress;

import java.util.List;

@Repository
public interface UserAddressRepository extends CrudRepository<UserAddress,Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="INSERT into user_address(type,user_id,address_id) values(?1,?2,?3)")
    void addAddressType(String type, int user_id, int address_id);

    @Query(nativeQuery = true,value = "Select address_id from user_address where user_id=(?1) and type='perm'")
    List<Integer> getAddressId(int userId);
}
