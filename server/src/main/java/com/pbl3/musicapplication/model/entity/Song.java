package com.pbl3.musicapplication.model.entity;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.model.SongModel;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.TableGenerator;
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
    @TableGenerator(name = "SongId_Gen", initialValue = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SongId_Gen")
    @Setter(AccessLevel.PRIVATE)
    private Integer songId;
    
    @Nonnull
    private String songName;
    
    @Nonnull
    @OneToOne(targetEntity = Artist.class)
    private Artist artist;

    @Nullable
    private LocalDateTime downloadDate;

    @Nonnull
    private String musicFilePath;
    @Nullable
    private String backgroundImageFilePath;

    public Song(SongModel songModel) {
        this.songName = songModel.getSongName();

        if (songModel.getArtistModel() != null)
            this.artist = new Artist(songModel.getArtistModel());
        
        this.downloadDate = songModel.getDownloadDate();
        this.musicFilePath = songModel.getMusicFilePath();
        this.backgroundImageFilePath = songModel.getBackgroundImageFilePath();
    }
    public void setArtist(@Nonnull Artist artist) {
        this.artist = artist;
    }
    public void setArtist(@Nonnull ArtistModel artistModel) {
        this.artist = new Artist(artistModel);
    }
    public void setDownloadDate(@Nonnull LocalDateTime localDateTime) {
        this.downloadDate = localDateTime;
    }
    public void setDownloadDate(int day, int month, int year, int hour, int minute, int second) {
        try {
            this.downloadDate = LocalDateTime.of(day, month, year, hour, minute, second);
        }
        catch (DateTimeException ex) {
            this.downloadDate = null;
        }
    }
    public void setDownloadDate(String downloadDateString) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            this.downloadDate = LocalDateTime.parse(downloadDateString, dateTimeFormatter);
        }
        catch (DateTimeException ex) {
            this.downloadDate = null;
        }
    }

    public boolean isValid() {
        return !(songName.isEmpty() || songName == null || artist == null || !artist.isValid() 
                || musicFilePath.isEmpty() || musicFilePath == null);
    }
}
