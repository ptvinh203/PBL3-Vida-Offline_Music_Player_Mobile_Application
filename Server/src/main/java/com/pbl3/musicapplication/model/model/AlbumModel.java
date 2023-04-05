package com.pbl3.musicapplication.model.model;

import java.util.ArrayList;
import java.util.List;

import com.pbl3.musicapplication.model.entity.Album;
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
public class AlbumModel {
    private Integer albumId;
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
    public SongModel findSongById(Integer songId) {
        if (this.songsAlbum != null) {
            for (SongModel songModel : songsAlbum) {
                if (songModel.getSongId().compareTo(songId) == 0) {
                    return songModel;
                }
            }
        }
        return null;
    }
}
