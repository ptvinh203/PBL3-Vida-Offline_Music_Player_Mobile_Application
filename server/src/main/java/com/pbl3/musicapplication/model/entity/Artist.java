package com.pbl3.musicapplication.model.entity;

import java.util.ArrayList;

import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.ArtistModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Integer artistID;
    
    private String artistName;
    private String artistImagePath;

    @OneToMany(targetEntity = Album.class)
    private ArrayList<Album> albums;

    public Artist(ArtistModel artistModel) {
        this.artistName = artistModel.getArtistName();
        this.artistImagePath = artistModel.getArtistImagePath();
        
        ArrayList<Album> tmp = new ArrayList<>();
        for (AlbumModel albumModel : artistModel.getAlbumModels()) {
            tmp.add(new Album(albumModel));
        }
        
        this.albums = tmp;
    }
    
    public void setAlbums(ArrayList<AlbumModel> albumModels) {
        ArrayList<Album> tmp = new ArrayList<>();
        for (AlbumModel albumModel : albumModels) {
            tmp.add(new Album(albumModel));
        }
        
        this.albums = tmp;
    }
    public boolean isValid() {
        return !(artistName == null || artistName.isEmpty());
    }
}
