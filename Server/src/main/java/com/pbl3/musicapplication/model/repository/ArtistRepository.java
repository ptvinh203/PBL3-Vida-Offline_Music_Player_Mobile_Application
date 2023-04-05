package com.pbl3.musicapplication.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.pbl3.musicapplication.model.entity.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    
}
