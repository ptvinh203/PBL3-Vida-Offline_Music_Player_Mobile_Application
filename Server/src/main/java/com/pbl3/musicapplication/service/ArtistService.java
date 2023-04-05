package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.model.ArtistModel;

import jakarta.annotation.Nonnull;


public interface ArtistService extends GenericService<Artist, ArtistModel>{
    List<String> getArtistNameList();
    Boolean updateAlbums(Integer artistId);
    Boolean updateSingleAndEpSongs(Integer artistId);
    Artist findAlbum(@Nonnull Integer albumId);
    Artist findSingleAndEpSong(@Nonnull Integer songId);
}
