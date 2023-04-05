package com.pbl3.musicapplication.model.entity;

import java.util.Date;

import com.pbl3.musicapplication.model.model.SongModel;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    
    @Nonnull
    private String songName;

    @Nullable
    @ManyToOne(targetEntity = Artist.class)
    private Artist artist;

    @Nullable
    @OneToOne(targetEntity = Album.class)
    private Album album;

    @Nullable
    @Temporal(TemporalType.DATE)
    private Date downloadDate;

    @Nonnull
    private String musicFilePath;

    @Nullable
    private String backgroundImageFilePath;

    
    public Song(SongModel songModel) {
        this.songName = songModel.getSongName();
        this.downloadDate = songModel.getDownloadDate();
        this.musicFilePath = songModel.getMusicFilePath();
        this.backgroundImageFilePath = songModel.getBackgroundImageFilePath();
    }
    public boolean isValid() {
        return !(songName == null || songName.isEmpty() ||
                musicFilePath == null|| musicFilePath.isEmpty());
    }
}
