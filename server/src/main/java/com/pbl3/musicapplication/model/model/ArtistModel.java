package com.pbl3.musicapplication.model.model;

import java.util.ArrayList;
import java.util.List;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.entity.Artist;

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
public class ArtistModel {
    @Setter(AccessLevel.PRIVATE)
    private Integer artistID;
    private String artistName;
    private String artistImagePath;

    @Setter(AccessLevel.NONE)
    private List<AlbumModel> albumModels;

    public ArtistModel(Artist entity) {
        this.artistID = entity.getArtistID();
        this.artistName = entity.getArtistName();
        this.artistImagePath = entity.getArtistImagePath();
        
        if (entity.getAlbums() != null) {
            List<AlbumModel> tmp = new ArrayList<>();
            for (Album album  : entity.getAlbums()) {
                tmp.add(new AlbumModel(album));
            }
    
            this.albumModels = tmp;
        }
    }
    public void setAlbumModels(@Nonnull List<Album> albums) {
        List<AlbumModel> tmp = new ArrayList<>();
        for (Album album : albums) {
            tmp.add(new AlbumModel(album));
        }
        this.albumModels = tmp;
    }
}
