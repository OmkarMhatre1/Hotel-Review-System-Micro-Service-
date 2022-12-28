package com.userService.UserService.services;

import com.userService.UserService.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get user with user ID
    User getUser(int userId);

    //delete
     public String deleteUser(int userId);

    //update

    User updateUser(User user);
}
