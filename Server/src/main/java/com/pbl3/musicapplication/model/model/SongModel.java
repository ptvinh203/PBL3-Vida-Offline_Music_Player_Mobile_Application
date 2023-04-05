package com.pbl3.musicapplication.model.model;


import java.util.Date;

import com.pbl3.musicapplication.model.entity.Song;

import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor  
@Getter@Setter
@Builder
public class SongModel {
    @Setter(AccessLevel.PRIVATE)
    private Integer songId;

    @Nonnull
    private String songName;

    private ArtistModel artist;
    private AlbumModel album;
    private Date downloadDate;

    @Nonnull
    private String musicFilePath;

    private String backgroundImageFilePath;

    public SongModel (Song entity) {
        this.songId = entity.getSongId(); 
        this.songName = entity.getSongName();
        this.downloadDate = entity.getDownloadDate();
        this.musicFilePath = entity.getMusicFilePath();
        this.backgroundImageFilePath = entity.getBackgroundImageFilePath();
    }
}
