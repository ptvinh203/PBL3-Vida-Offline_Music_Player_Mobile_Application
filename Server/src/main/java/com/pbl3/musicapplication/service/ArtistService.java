package com.pbl3.musicapplication.service;

import java.util.List;

import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.model.ArtistModel;

import jakarta.annotation.Nonnull;


public interface ArtistService extends GenericService<Artist, ArtistModel>{
    List<String> getArtistNameList();
    Boolean updateAlbums(Integer artistId);
    Boolean updateSingleAndEpSongs(Integer artistId);
    ArtistModel findAlbum(@Nonnull Integer albumId);
    ArtistModel findSingleAndEpSong(@Nonnull Integer songId);
    ArtistModel setArtistImage(@Nonnull Integer artistId, @Nonnull Integer artistImageFileId);
}
