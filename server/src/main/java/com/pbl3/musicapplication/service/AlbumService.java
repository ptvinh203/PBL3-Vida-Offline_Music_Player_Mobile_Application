package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.SongModel;

public interface AlbumService extends GenericService<Album, AlbumModel>{
    List<String> getAlbumNameList();
    List<SongModel> getAllSongsList(Integer id);
    Boolean updateArtist(Integer artistId, Integer albumId, Boolean checkAdd);
}
