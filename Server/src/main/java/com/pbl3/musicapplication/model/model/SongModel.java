package com.pbl3.musicapplication.model.model;


import java.util.Date;

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
    private Date downloadDate;

    public SongModel (Song entity) {
        this.songId = entity.getSongId(); 
        this.songName = entity.getSongName();
        this.downloadDate = entity.getDownloadDate();
    }
}
