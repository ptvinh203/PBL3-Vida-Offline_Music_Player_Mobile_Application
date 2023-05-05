package com.pbl3.musicapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbl3.musicapplication.model.model.UserModel;
import com.pbl3.musicapplication.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> findById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }
    @GetMapping("/{password}/checkAuthentication")
    public ResponseEntity<UserModel> checkAuthentication(@RequestBody UserModel userModel, @PathVariable String password) {
        return ResponseEntity.ok(userService.checkAuthentication(userModel, password));
    }

    @PostMapping(value =  "/{password}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<UserModel> create(@RequestBody UserModel userModel, @PathVariable String password) {
        UserModel create = userService.create(userModel, password);
        if (create != null) {
            return ResponseEntity.ok(create);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{userId}") 
    public ResponseEntity<String> deleteById(@PathVariable Integer userId) {
        UserModel userModel = userService.findById(userId);
        if (userModel == null)
            return new ResponseEntity<>("Not found object", HttpStatus.NO_CONTENT);
        userService.deleteById(userId);
            
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT); 
    }

    @PutMapping("/changePassword/{password}")
    public ResponseEntity<UserModel> changePassword(@RequestBody UserModel userModel, @PathVariable String password) {
        UserModel update = userService.changePassword(userModel, password);
        if (update == null) {
            return ResponseEntity.badRequest().body(null);
        } 
        return new ResponseEntity<>(update, HttpStatus.NO_CONTENT);
    }
}
