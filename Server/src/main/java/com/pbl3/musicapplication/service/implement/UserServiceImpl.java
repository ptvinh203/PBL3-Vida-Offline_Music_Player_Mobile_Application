package com.pbl3.musicapplication.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.model.entity.User;
import com.pbl3.musicapplication.model.model.LoginRequest;
import com.pbl3.musicapplication.model.model.RegisterRequest;
import com.pbl3.musicapplication.model.model.UserModel;
import com.pbl3.musicapplication.model.repository.UserRepository;
import com.pbl3.musicapplication.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel create(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        try {
            if (user.isValid()) {
                user.setPassword(registerRequest.getPassword());
                user.setFullName(registerRequest.getFullName());
                user.setPhoneNumber(registerRequest.getPhoneNumber());
                User saved = userRepository.save(user);
                if (saved != null)
                    return new UserModel(saved);
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public UserModel login(LoginRequest loginRequest) {
        UserModel userModel = null;
        for (User user : userRepository.findAll()) {
            if (user.getUsername().compareTo(loginRequest.getUsername()) == 0
                    && user.getPassword().compareTo(loginRequest.getPassword()) == 0) {
                userModel = new UserModel(user);
                userModel.setAuthentication(true);
                break;
            }
        }
        return userModel;
    }

    @Override
    public UserModel findById(Integer userId) {
        User fromDB = userRepository.findById(userId).orElse(null);
        if (fromDB != null)
            return new UserModel(fromDB);

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
