package com.pbl3.musicapplication.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.SongModel;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @Nonnull
    private String albumName;

    @Nullable
    @ManyToOne(targetEntity = Artist.class)
    private Artist artist;

    @Nullable
    @OneToMany(targetEntity = Song.class)
    private List<Song> songsAlbum;
    
    public Album(AlbumModel albumModel) {
        this.albumName = albumModel.getAlbumName();
        
        if (albumModel.getSongsAlbum() != null) {
            List<Song> tmp = new ArrayList<>();
            for (SongModel songModel : albumModel.getSongsAlbum()) {
                tmp.add(new Song(songModel));
            }
            this.songsAlbum = tmp;
        }
    }
    
    public boolean isValid() {
        return !(albumName == null || albumName.isEmpty());
    }
}
