package com.pbl3.musicapplication.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.ArtistModel;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TableGenerator;
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
    @TableGenerator(name = "ArtistId_Gen", initialValue = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ArtistId_Gen")
    @Setter(AccessLevel.PRIVATE)
    private Integer artistID;
    
    @Nonnull
    private String artistName;
    @Nullable
    private String artistImagePath;

    @Nullable
    @Setter(AccessLevel.NONE)
    @OneToMany(targetEntity = Album.class)
    private List<Album> albums;

    public Artist(ArtistModel artistModel) {
        this.artistName = artistModel.getArtistName();
        this.artistImagePath = artistModel.getArtistImagePath();
        
        if (artistModel.getAlbumModels() != null) {
            List<Album> tmp = new ArrayList<>();
            for (AlbumModel albumModel : artistModel.getAlbumModels()) {
                tmp.add(new Album(albumModel));
            }
            
            this.albums = tmp;
        }
    }
    
    public void setAlbums(@Nonnull List<AlbumModel> albumModels) {
        List<Album> tmp = new ArrayList<>();
        for (AlbumModel albumModel : albumModels) {
            tmp.add(new Album(albumModel));
        }
        
        this.albums = tmp;
    }
    public boolean isValid() {
        return !(artistName == null || artistName.isEmpty());
    }
}
