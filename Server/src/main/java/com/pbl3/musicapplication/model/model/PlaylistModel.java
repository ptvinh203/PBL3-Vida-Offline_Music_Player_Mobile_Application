package com.pbl3.musicapplication.model.model;

import java.util.ArrayList;
import java.util.List;

import com.pbl3.musicapplication.model.entity.Playlist;
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
public class PlaylistModel {
    @Setter(AccessLevel.PRIVATE)
    private Integer playlistId;
    private String playlistName;
    private List<SongModel> songsPlaylist;

    public PlaylistModel(Playlist entity) {
        this.playlistId = entity.getPlaylistId();
        this.playlistName = entity.getPlaylistName();
        
        if (entity.getSongsPlaylist() != null) {
            List<SongModel> tmp = new ArrayList<>();
            for (Song song : entity.getSongsPlaylist()) {
                tmp.add(new SongModel(song));
            }
            this.songsPlaylist = tmp;
        }
    }
    public void convertToSongModels(@Nonnull List<Song> songs) {
        List<SongModel> tmp = new ArrayList<>();
        for (Song song : songs) {
            tmp.add(new SongModel(song));
        }
        this.songsPlaylist = tmp;
    }
}
