package com.pbl3.musicapplication.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.pbl3.musicapplication.model.entity.Song;


public interface SongRepository extends CrudRepository<Song, Integer> {
    
}
