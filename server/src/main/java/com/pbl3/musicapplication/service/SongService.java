package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.SongModel;


public interface SongService extends GenericService<Song, SongModel>{
    List<String> getSongNameList();
    Boolean updateArtist(Integer artistId, Integer songId, Boolean checkAdd);
}

