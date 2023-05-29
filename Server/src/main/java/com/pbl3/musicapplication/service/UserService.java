package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.model.UserRequest;
import com.pbl3.musicapplication.model.model.UserModel;

public interface UserService {
    UserModel create(UserRequest userRequest);

    UserModel login(UserRequest loginRequest);

    UserModel findById(Integer userId);

    List<UserModel> findAll();

    void deleteById(Integer userId);

    UserModel changePassword(UserModel userModel, String password);
}
