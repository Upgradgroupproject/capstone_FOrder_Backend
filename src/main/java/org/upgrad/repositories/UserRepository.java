package org.upgrad.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.upgrad.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.UserAuthToken;

// This repository interface is responsible for the interaction between the user service with the user database

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(nativeQuery = true,value="SELECT PASSWORD FROM USERS WHERE contact_number=?1")
    String findUserPassword(String contactNumber);

    @Query(nativeQuery = true,value = "SELECT * FROM USERS WHERE contact_number=?1")
    User findUser(String contactNumber);

    @Query(nativeQuery = true,value = "SELECT contact_number  FROM USERS WHERE upper(contact_number)=upper(?1)")
    String getContactNumber(String contactNumber);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "INSERT  INTO USERS(firstname,lastname,email,contact_number,password) values (?1,?2,?3,?4,?5)")
    void createUser(String firstName,String lastName,String emailField,String contactNumber,String sha256hex );

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update USERS set firstname=(?1) , lastname=(?2) where id=(?3)")
    void updateUser(String firstName,String lastName,int userID );

    @Query(nativeQuery = true,value = "SELECT * FROM USERS WHERE id=?1")
    User findUserByID(int userID);

    @Query(nativeQuery = true,value = "SELECT password FROM USERS WHERE id=?1")
    String findPasswordById(int userID);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update USERS set password=(?1)where id=(?2)")
    void updatePassword(String newPassword,int userID );
}

