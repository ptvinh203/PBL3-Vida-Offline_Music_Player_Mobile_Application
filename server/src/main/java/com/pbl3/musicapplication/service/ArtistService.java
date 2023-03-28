package com.pbl3.musicapplication.service;

import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.model.ArtistModel;

public interface ArtistService {
    Artist create(ArtistModel artistModel);
    Artist update(Integer id, ArtistModel artistModel);
    void deleteById(Integer id);
    ArtistModel findById(Integer id);
}
