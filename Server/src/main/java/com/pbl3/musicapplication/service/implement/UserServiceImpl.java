package com.pbl3.musicapplication.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.model.entity.User;
import com.pbl3.musicapplication.model.model.UserModel;
import com.pbl3.musicapplication.model.repository.UserRepository;
import com.pbl3.musicapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel create(UserModel userModel, String password) {
        User user = new User(userModel);
        if (user.isValid()) {
            user.setPassword(password);

            User saved = userRepository.save(user);
            if (saved != null) return new UserModel(saved);
        }
        return null;
    }

    @Override
    public UserModel checkAuthentication(UserModel userModel, String password) {
        User user = userRepository.findById(userModel.getUserId()).orElse(null);
        if (user != null && user.getPassword().compareTo(password) == 0) {
            userModel.setAuthentication(true);
            return userModel;
        }
        userModel.setAuthentication(false);
        return userModel;
    }

    @Override
    public UserModel findById(Integer userId) {
        User fromDB = userRepository.findById(userId).orElse(null);
        if (fromDB != null) return new UserModel(fromDB);

        return null;
    }

    @Override
    public List<UserModel> findAll() {
        List<UserModel> result = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            result.add(new UserModel(user));
        }
        return result;
    }

    @Override
    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserModel changePassword(UserModel userModel, String password) {
        User user = userRepository.findById(userModel.getUserId()).orElse(null);
        if (user != null) {
            user.setPassword(password);
            return new UserModel(userRepository.save(user));
        }
        return null;
    }
    
}
