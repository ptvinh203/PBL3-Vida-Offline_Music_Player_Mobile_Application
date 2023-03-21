package com.pbl3.musicapplication.model.entity;

import java.util.ArrayList;

import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.SongModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;


@AllArgsConstructor@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Integer albumId;

    @OneToOne(targetEntity = Artist.class)
    private Artist artist;

    @OneToMany(targetEntity = Song.class)
    private ArrayList<Song> songsAlbum;
    
    public Album (AlbumModel albumModel) {
        this.artist = albumModel.getArtist();

        ArrayList<Song> tmp = new ArrayList<>();
        for (SongModel songModel : albumModel.getSongModels()) {
            tmp.add(new Song(songModel));
        }
        this.songsAlbum = tmp;
    }

    public void setSongsAlbum(ArrayList<SongModel> songModels){
        ArrayList<Song> tmp = new ArrayList<>();
        for (SongModel songModel : songModels) {
            tmp.add(new Song(songModel));
        }
        this.songsAlbum = tmp;
    }
    public boolean isValid() {
        return artist.isValid();
    }
}
