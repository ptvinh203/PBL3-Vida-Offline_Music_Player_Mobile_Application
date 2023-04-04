package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.ArtistModel;

import jakarta.annotation.Nonnull;


public interface ArtistService extends GenericService<Artist, ArtistModel>{
    List<String> getArtistNameList();
    Artist updateAlbums(Integer id, Boolean checkAdd, @Nonnull Album album);
    Artist updateSingleAndEpSongs(Integer id, Boolean checkAdd, @Nonnull Song song);
    Artist findAlbum(@Nonnull Integer albumId);
    Artist findSingleAndEpSong(@Nonnull Integer songId);
}
