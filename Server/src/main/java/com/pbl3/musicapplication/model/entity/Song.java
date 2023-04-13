package com.pbl3.musicapplication.model.entity;

import java.util.Date;

import com.pbl3.musicapplication.model.model.SongModel;

import jakarta.persistence.CascadeType;
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
    
    private String songName;

    @ManyToOne(targetEntity = Artist.class)
    private Artist artist;

    @OneToOne(targetEntity = Album.class)
    private Album album;

    @Temporal(TemporalType.DATE)
    private Date downloadDate;

    @OneToOne(targetEntity = MyFile.class, cascade = CascadeType.REMOVE)
    private MyFile musicFile;

    @OneToOne(targetEntity = MyFile.class, cascade = CascadeType.REMOVE)
    private MyFile backgroundImageFile;

    
    public Song(SongModel songModel) {
        this.songName = songModel.getSongName();
        this.downloadDate = songModel.getDownloadDate();
    }
    public boolean isValid() {
        return !(songName == null || songName.isEmpty());
    }
}
