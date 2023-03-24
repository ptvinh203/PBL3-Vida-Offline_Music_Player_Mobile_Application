package com.pbl3.musicapplication.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.pbl3.musicapplication.model.entity.Album;

public interface AlbumRepository extends CrudRepository<Album, Integer>{
    
}
