package com.pbl3.musicapplication.model.model;

import java.util.ArrayList;

import com.pbl3.musicapplication.model.entity.Playlist;
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
public class PlaylistModel {
    @Setter(AccessLevel.PRIVATE)
    private Integer playlistId;
    private String playlistName;
    private ArrayList<SongModel> songModels;

    public PlaylistModel(Playlist entity) {
        this.playlistId = entity.getPlaylistId();
        this.playlistName = entity.getPlaylistName();
        
        ArrayList<SongModel> tmp = new ArrayList<>();
        for (Song song : entity.getSongsPList()) {
            tmp.add(new SongModel(song));
        }
        
        this.songModels = tmp;
    }
}
