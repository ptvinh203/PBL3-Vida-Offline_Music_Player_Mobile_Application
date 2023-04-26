package com.pbl3.musicapplication.model.model;

import java.util.ArrayList;
import java.util.List;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.entity.Song;

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
    private Integer artistId;
    private String artistName;
    private String artistImageUrl;
    private List<AlbumModel> albums;
    private List<SongModel> singleAndEpSongs;

    public ArtistModel(Artist entity) {
        this.artistId = entity.getArtistId();
        this.artistName = entity.getArtistName();

        if (entity.getArtistImage() != null) {
            this.artistImageUrl = (new MyFileModel(entity.getArtistImage()).getFileDownloadUri());
        }

        if (entity.getAlbums() != null) {
            List<AlbumModel> tmp = new ArrayList<>();
            for (Album album  : entity.getAlbums()) {
                tmp.add(new AlbumModel(album));
            }
    
            this.albums = tmp;
        }
        if (entity.getSingleAndEpSongs() != null) {
            List<SongModel> tmp = new ArrayList<>();
            for (Song song : entity.getSingleAndEpSongs()) {
                tmp.add(new SongModel(song));
            }

            this.singleAndEpSongs = tmp;
        }
    }
    public AlbumModel findAlbumById(Integer albumId) {
        if (this.albums != null) {
            for (AlbumModel albumModel : this.albums) 
                if (albumModel.getAlbumId().compareTo(albumId) == 0)
                    return albumModel;
        }
        return null;
    }
    public SongModel findSongById(Integer songId, Boolean checkSingleSong) {
        if (checkSingleSong) {
            if (this.singleAndEpSongs != null) {
                for (SongModel songModel : this.singleAndEpSongs) {
                    if (songModel.getSongId().compareTo(songId) == 0) 
                        return songModel;
                }
            }
        }
        else {
            if (this.albums != null) {
                for (AlbumModel albumModel : this.albums) {
                    return albumModel.findSongById(songId);
                }
            }
        }
        return null;
    }
}
