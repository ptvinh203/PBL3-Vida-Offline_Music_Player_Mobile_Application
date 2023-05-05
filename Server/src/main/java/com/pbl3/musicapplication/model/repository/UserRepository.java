package com.pbl3.musicapplication.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.pbl3.musicapplication.model.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
}
