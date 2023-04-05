package com.pbl3.musicapplication.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.pbl3.musicapplication.model.model.PlaylistModel;
import com.pbl3.musicapplication.model.model.SongModel;

import io.micrometer.common.lang.Nullable;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Integer playlistId;

    @Nonnull
    private String playlistName;
    
    @Nullable
    @ManyToMany(targetEntity = Song.class)
    private List<Song> songsPlaylist;

    public Playlist(PlaylistModel playlistModel) {
        this.playlistName = playlistModel.getPlaylistName();
        if (playlistModel.getSongsPlaylist() != null) {
            List<Song> tmp = new ArrayList<>();
            for (SongModel songModel : playlistModel.getSongsPlaylist()) {
                tmp.add(new Song(songModel));   
            }
            this.songsPlaylist = tmp;
        }
    }
    public boolean isValid() {
        return !(playlistName == null || playlistName.isEmpty());
    }
}
