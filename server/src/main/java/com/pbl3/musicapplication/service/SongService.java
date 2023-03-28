package com.pbl3.musicapplication.service;

import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.SongModel;

public interface SongService {
    Song create(SongModel songModel);
    Song update(Integer id, SongModel songModel);
    void deleteById(Integer id);
    SongModel findById(Integer id);
}

