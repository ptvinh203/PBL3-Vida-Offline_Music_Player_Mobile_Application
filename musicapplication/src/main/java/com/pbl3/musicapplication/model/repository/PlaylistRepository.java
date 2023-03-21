package com.pbl3.musicapplication.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.pbl3.musicapplication.model.entity.Playlist;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer>{
    
}
