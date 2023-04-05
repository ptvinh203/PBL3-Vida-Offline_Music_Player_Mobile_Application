package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.model.SongModel;

import jakarta.annotation.Nonnull;

public interface AlbumService extends GenericService<Album, AlbumModel>{
    List<String> getAlbumNameList();
    List<SongModel> getAllSongsList(Integer id);
    Boolean updateArtist(Integer artistId, Integer albumId, Boolean checkAdd);
    Boolean updateSongs(@Nonnull Integer albumId, Boolean checkAdd);
    Album setArtist(@Nonnull Integer albumId, @Nonnull Integer artistId);
    ArtistModel getArtistAlbum(Integer albumId);

}