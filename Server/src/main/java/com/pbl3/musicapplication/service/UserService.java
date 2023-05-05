package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.model.UserModel;

public interface UserService {
    UserModel create(UserModel userModel, String password);
    UserModel checkAuthentication(UserModel userModel, String password);
    UserModel findById(Integer userId);
    List<UserModel> findAll();
    void deleteById(Integer userId);
    UserModel changePassword(UserModel userModel, String password);
}
