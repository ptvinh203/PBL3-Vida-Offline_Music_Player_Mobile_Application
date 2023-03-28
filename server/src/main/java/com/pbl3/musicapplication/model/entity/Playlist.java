package com.pbl3.musicapplication.model.entity;

import java.util.ArrayList;

import com.pbl3.musicapplication.model.model.PlaylistModel;
import com.pbl3.musicapplication.model.model.SongModel;

import io.micrometer.common.lang.Nullable;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TableGenerator;
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
    @TableGenerator(name = "PlaylistId_Gen", initialValue = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PlaylistId_Gen")
    @Setter(AccessLevel.PRIVATE)
    private Integer playlistId;

    @Nonnull
    private String playlistName;
    
    @Nullable
    @OneToMany(targetEntity = Song.class)
    private ArrayList<Song> songsPList;

    public Playlist(PlaylistModel playlistModel) {
        ArrayList<Song> tmp = new ArrayList<>();

        for (SongModel songModel : playlistModel.getSongModels()) {
            tmp.add(new Song(songModel));   
        }

        this.songsPList = tmp;
    }
    public void setSongsPList(ArrayList<SongModel> songModels) {
        ArrayList<Song> tmp = new ArrayList<>();
        for (SongModel songModel : songModels) {
            tmp.add(new Song(songModel));   
        }
        this.songsPList = tmp;
    }
    public boolean isValid() {
        return (!playlistName.isEmpty() && playlistName != null);
    }
}
