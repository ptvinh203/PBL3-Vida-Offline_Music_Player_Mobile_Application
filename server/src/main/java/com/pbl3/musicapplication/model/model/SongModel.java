package com.pbl3.musicapplication.model.model;


import java.time.DateTimeException;
import java.time.LocalDateTime;

import com.pbl3.musicapplication.model.entity.Artist;
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

    @Nonnull
    private ArtistModel artistModel;

    private LocalDateTime downloadDate;

    @Nonnull
    private String musicFilePath;
    private String backgroundImageFilePath;

    public SongModel (Song entity) {
        this.songId = entity.getSongId(); 
        this.songName = entity.getSongName();
        this.artistModel = new ArtistModel(entity.getArtist());
        this.downloadDate = entity.getDownloadDate();
        this.musicFilePath = entity.getMusicFilePath();
        this.backgroundImageFilePath = entity.getBackgroundImageFilePath();
    }
    public void setArtistModel(@Nonnull ArtistModel artistModel) {
        this.artistModel = artistModel;
    }
    public void setArtistModel(@Nonnull Artist artist) {
        this.artistModel = new ArtistModel(artist);
    }
    public void setDownloadDate(@Nonnull LocalDateTime localDateTime) {
        this.downloadDate = localDateTime;
    }
    public void setDownloadDate(int day, int month, int year, int hour, int minute, int second) {
        try {
            downloadDate = LocalDateTime.of(day, month, year, hour, minute, second);
        }
        catch (DateTimeException ex) {
            downloadDate = null;
        }
    }
    public void setDownloadDate(String downloadDateString) {
        try {
            downloadDate = LocalDateTime.parse(downloadDateString);
        }
        catch (DateTimeException ex) {
            downloadDate = null;
        }
    }
}
