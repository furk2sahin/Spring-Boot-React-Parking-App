package com.furkannsahin.parking.service;

import com.furkannsahin.parking.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User createUser(User user);
}
