package com.pbl3.musicapplication.model.entity;

import java.sql.Date;

import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.model.SongModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@Builder
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Integer songId;
    
    private String songName;
    
    @ManyToOne(targetEntity = Artist.class)
    private Artist artist;

    private Date downloadDate;
    private String musicFilePath;
    private String backgroundImageFilePath;

    public Song(SongModel songModel) {
        this.songName = songModel.getSongName();

        this.artist = new Artist(songModel.getArtistModel());
        
        this.downloadDate = songModel.getDownloadDate();
        this.musicFilePath = songModel.getMusicFilePath();
        this.backgroundImageFilePath = songModel.getBackgroundImageFilePath();
    }
    public void setArtist(ArtistModel artistModel) {
        this.artist = new Artist(artistModel);
    }
    public boolean isValid() {
        return !(songName.isEmpty() || songName == null || !artist.isValid() || downloadDate == null 
                || musicFilePath.isEmpty() || musicFilePath == null);
    }
}
