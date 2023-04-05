package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.model.SongModel;

import jakarta.annotation.Nonnull;


public interface SongService extends GenericService<Song, SongModel>{
    List<String> getSongNameList();
    Boolean updateArtist(Integer artistId, Integer songId, Boolean checkAdd);
    Boolean updateAlbum(Integer albumId, Integer songId, Boolean checkAdd);
    Song setArtist(@Nonnull Integer songId, @Nonnull Integer artistId);
    Song setAlbum(@Nonnull Integer songId, @Nonnull Integer albumId);
    Song removeAlbum(@Nonnull Integer songId);
    ArtistModel getArtistSong(Integer songId);
    AlbumModel getAlbumSong(Integer songId);
}