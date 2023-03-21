package com.pbl3.musicapplication.model.model;

import java.sql.Date;

import com.pbl3.musicapplication.model.entity.Song;

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
    private Integer songId;
    private String songName;
    private ArtistModel artistModel;
    private Date downloadDate;
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
}
