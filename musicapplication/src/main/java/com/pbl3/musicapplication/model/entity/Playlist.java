package com.pbl3.musicapplication.model.entity;

import java.util.ArrayList;

import com.pbl3.musicapplication.model.model.PlaylistModel;
import com.pbl3.musicapplication.model.model.SongModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    // private int getIndex(Song s) {
    //     for (int i = 0; i < songsPList.size(); i++)
    //         if (songsPList.get(i).getSongId().compareTo(s.getSongId()) == 0)
    //             return i;
    //     return -1;
    // }

    // public int getLength() {
    //     return songsPList.size();
    // }

    // public void addSong(Song s) throws Exception {
    //     if (getIndex(s) != -1) 
    //         throw new Exception("This song has been in your playlist"); 
    //     else songsPList.add(s);
    // }
    // public void removeSong(Song s) throws Exception {
    //     if (getIndex(s) == -1)
    //         throw new Exception("This song hasn't been in your playlist");
    //     else songsPList.remove(s);
    // }
    // public Song search(Song s) {

    //     return new Song();
    // }
}
