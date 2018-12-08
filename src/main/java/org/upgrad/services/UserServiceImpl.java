package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.User;
import org.upgrad.models.UserAuthToken;
import org.upgrad.repositories.UserAuthTokenRepository;
import org.upgrad.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String findUserPassword(String contactNumber) {
        return userRepository.findUserPassword(contactNumber);
    }

    @Override
    public User findUser(String contactNumber) {
        return userRepository.findUser(contactNumber);
    }

    @Override
    public boolean matchExpression(String option,String values) {

        switch (option) {
            case "email":
                String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
                java.util.regex.Matcher m = p.matcher(values);
                return m.matches();

            case "contact":
                Boolean bool=false;
                Pattern pattern = Pattern.compile("\\d{10}");
                Matcher matcher=pattern.matcher(values);
                if(matcher.matches())
                {
                    bool=true;
                    return bool;
                }
                else return bool;

            case "password":
                Boolean bool1=false;
                String patternPassword="((?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&*!^]).{8,20})";
                Pattern passwordPat=Pattern.compile(patternPassword);
                Matcher pwdMatcher=passwordPat.matcher(values);
                return pwdMatcher.matches();

            case "dafault":
                return false;

        }
        return false;
    }

    @Override
    public String getContactNumber(String contactNumber) {
        String contact=userRepository.getContactNumber(contactNumber);
        return contact;
    }

    @Override
    public void createUsers(String firstName, String lastName, String emailField, String contactNumber, String password) {
        userRepository.createUser(firstName, lastName, emailField, contactNumber,password);
    }


}
