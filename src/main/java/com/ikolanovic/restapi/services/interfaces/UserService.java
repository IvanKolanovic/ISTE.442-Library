package com.ikolanovic.restapi.services.interfaces;


import com.ikolanovic.restapi.models.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUser(Long id);

    List<User> getAllUsers();

    boolean deleteUser(Long userId);

    boolean isEmailInUse(String email);

    User updateUser(Long id, User input);

}
