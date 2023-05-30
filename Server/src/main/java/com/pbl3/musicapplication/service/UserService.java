package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.model.LoginRequest;
import com.pbl3.musicapplication.model.model.RegisterRequest;
import com.pbl3.musicapplication.model.model.UserModel;

public interface UserService {
    UserModel create(RegisterRequest registerRequest);

    UserModel login(LoginRequest loginRequest);

    UserModel findById(Integer userId);

    List<UserModel> findAll();

    void deleteById(Integer userId);

    UserModel changePassword(UserModel userModel, String password);
}
