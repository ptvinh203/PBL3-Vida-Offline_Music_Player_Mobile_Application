package com.pbl3.musicapplication.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.model.SongModel;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter@Getter
@Builder
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Integer artistId;
    
    @Nonnull
    private String artistName;

    @Nullable
    private String artistImagePath;

    @Nullable
    @OneToMany(targetEntity = Album.class, cascade = CascadeType.REMOVE)
    private List<Album> albums;

    @Nullable
    @ManyToMany(targetEntity = Song.class, cascade = CascadeType.REMOVE)
    private List<Song> singleAndEpSongs;

    public Artist(ArtistModel artistModel) {
        this.artistName = artistModel.getArtistName();
        this.artistImagePath = artistModel.getArtistImagePath();
        
        if (artistModel.getAlbums() != null) {
            List<Album> tmp = new ArrayList<>();
            for (AlbumModel albumModel : artistModel.getAlbums()) {
                tmp.add(new Album(albumModel));
            }
            
            this.albums = tmp;
        } 
        if (artistModel.getSingleAndEpSongs() != null) {
            List<Song> tmp = new ArrayList<>();
            for (SongModel songModel : artistModel.getSingleAndEpSongs()) {
                tmp.add(new Song(songModel));
            }

            this.singleAndEpSongs = tmp;
        }
    }
    public boolean isValid() {
        return !(artistName == null || artistName.isEmpty());
    }
}
