package org.upgrad.services;

import org.upgrad.models.User;
import org.upgrad.models.UserAuthToken;

import java.util.Optional;

/*
 * This UserService interface gives the list of all the service that exist in the user service implementation class.
 * Controller class will be calling the service methods by this interface.
 */
public interface UserService {

    String findUserPassword(String contactNumber);

    User findUser(String contactNumber);
    public boolean matchExpression(String option,String values);
    public String getContactNumber(String contactNumber);
    public void createUsers(String firstName,String lastName,String emailField,String contactNumber,String password );
    public User updateUser(String firstName,String lastName,int userID);
    User getUserById(int userID);
    public String getUserPassword(int userId);
    public void updatePassword(String newPassword,int userID);
    public void addAddressType(String type, int user_id, int address_id);



}
