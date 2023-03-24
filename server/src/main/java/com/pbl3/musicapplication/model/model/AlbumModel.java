package com.pbl3.musicapplication.model.model;

import java.util.ArrayList;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.entity.Song;

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
public class AlbumModel {
    @Setter(AccessLevel.PRIVATE)
    private Integer albumId;
    
    private Artist artist;
    private ArrayList<SongModel> songModels; 
    
    public AlbumModel(Album entity) {
        this.albumId = entity.getAlbumId();
        this.artist = entity.getArtist();

        ArrayList<SongModel> tmp = new ArrayList<>();
        for (Song song : entity.getSongsAlbum()) {
            tmp.add(new SongModel(song));
        }

        this.songModels = tmp;
    }
}
