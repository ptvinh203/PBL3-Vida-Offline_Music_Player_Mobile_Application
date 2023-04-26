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
    private String musicFileUrl;
    private String backgroundImageFileUrl;

    public SongModel (Song entity) {
        this.songId = entity.getSongId(); 
        this.songName = entity.getSongName();
        this.downloadDate = entity.getDownloadDate();

        if (entity.getMusicFile() != null) {
            this.musicFileUrl = (new MyFileModel(entity.getMusicFile()).getFileDownloadUri());
        }

        if (entity.getBackgroundImageFile() != null) {
            this.backgroundImageFileUrl = (new MyFileModel(entity.getBackgroundImageFile()).getFileDownloadUri());
        }
    }
}
