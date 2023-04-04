package com.pbl3.musicapplication.model.model;

import java.util.ArrayList;
import java.util.List;

import com.pbl3.musicapplication.model.entity.Album;
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
public class AlbumModel {
    @Setter(AccessLevel.PRIVATE)
    private Integer albumId;

    @Nonnull
    private String albumName;

    private List<SongModel> songsAlbum; 
    
    public AlbumModel(Album entity) {
        this.albumId = entity.getAlbumId();
        this.albumName = entity.getAlbumName();

        if (entity.getSongsAlbum() != null) {
            List<SongModel> tmp = new ArrayList<>();
            for (Song song : entity.getSongsAlbum()) {
                tmp.add(new SongModel(song));
            }
    
            this.songsAlbum = tmp;
        }
    }
    public void convertToSongModels(@Nonnull List<Song> songs) {
        List<SongModel> tmp = new ArrayList<>();
        for (Song song : songs) {
            tmp.add(new SongModel(song));
        }

        this.songsAlbum = tmp;
    }
}
