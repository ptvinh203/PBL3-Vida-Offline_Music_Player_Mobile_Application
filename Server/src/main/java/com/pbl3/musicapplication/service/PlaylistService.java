package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.entity.Playlist;
import com.pbl3.musicapplication.model.model.PlaylistModel;
import com.pbl3.musicapplication.model.model.SongModel;

public interface PlaylistService extends GenericService<Playlist, PlaylistModel> {
    List<SongModel> getAllSongsList(Integer id);

    List<String> getPlayListNameList();
}
