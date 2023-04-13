package com.pbl3.musicapplication.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.pbl3.musicapplication.model.entity.MyFile;

public interface MyFileRepository extends CrudRepository<MyFile, Integer>{
    
}
