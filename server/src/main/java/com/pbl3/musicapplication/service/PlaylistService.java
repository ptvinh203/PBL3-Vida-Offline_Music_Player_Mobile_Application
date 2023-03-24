package com.pbl3.musicapplication.service;

import com.pbl3.musicapplication.model.entity.Playlist;
import com.pbl3.musicapplication.model.model.PlaylistModel;

public interface PlaylistService {
    Playlist create(PlaylistModel playlistModel);
    Playlist update(Integer id, PlaylistModel playlistModel);
    void delete(Integer id);
    Playlist findById(Integer id);   
}
