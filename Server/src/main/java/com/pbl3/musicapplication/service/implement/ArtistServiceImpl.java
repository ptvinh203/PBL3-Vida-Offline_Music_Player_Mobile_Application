package com.pbl3.musicapplication.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.model.repository.AlbumRepository;
import com.pbl3.musicapplication.model.repository.ArtistRepository;
import com.pbl3.musicapplication.model.repository.SongRepository;
import com.pbl3.musicapplication.service.AlbumService;
import com.pbl3.musicapplication.service.ArtistService;
import com.pbl3.musicapplication.service.SongService;

import jakarta.annotation.Nonnull;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    @Override
    public Artist create(@Nonnull ArtistModel artistModel) {
        Artist artist = new Artist(artistModel);
        if (!artist.isValid()) return null;

        if (artistModel.getAlbums() != null) {
            List<Album> lAlbums = new ArrayList<>();
            for (AlbumModel albumModel : artistModel.getAlbums()) {
                Album album;
                if (albumModel.getAlbumId() == null) {
                    album = albumService.create(albumModel);
                }
                else {
                    album = albumRepository.findById(albumModel.getAlbumId()).orElse(null);
                    if (album == null) {
                        album = albumService.create(albumModel);
                    }
                }
                if (album != null) lAlbums.add(album);
            }
            artist.setAlbums(lAlbums);
        }

        if (artistModel.getSingleAndEpSongs() != null) {
            List<Song> lSongs = new ArrayList<>();
            for (SongModel songModel : artistModel.getSingleAndEpSongs()) {
                Song song;
                if (songModel.getSongId() == null) {
                    song = songService.create(songModel);
                }
                else {
                    song = songRepository.findById(songModel.getSongId()).orElse(null);
                    if (song == null) {
                        song = songService.create(songModel);
                    }

                }
                lSongs.add(song);
            }
            artist.setSingleAndEpSongs(lSongs);
        }
        return artistRepository.save(artist);
    }

    @Override
    public Artist update(Integer id, @Nonnull ArtistModel artistModel) {
        Artist fromDB = artistRepository.findById(id).orElse(null);
        if (fromDB != null) {
            fromDB.setArtistName(artistModel.getArtistName());
            
            if (!fromDB.isValid()) return null;

            if (artistModel.getAlbums() != null) {
                List<Album> lAlbums = new ArrayList<>();
                for (AlbumModel albumModel : artistModel.getAlbums()) {
                    Album album;
                    if (albumModel.getAlbumId() == null) {
                        album = albumService.create(albumModel);
                    }
                    else {
                        album = albumRepository.findById(albumModel.getAlbumId()).orElse(null);
                        if (album == null) {
                            album = albumService.create(albumModel);
                        }
                    }
                    lAlbums.add(album);
                }
                fromDB.setAlbums(lAlbums);
            }
            if (artistModel.getSingleAndEpSongs() != null) {
                List<Song> lSongs = new ArrayList<>();
                for (SongModel songModel : artistModel.getSingleAndEpSongs()) {
                    Song song;
                    if (songModel.getSongId() == null) {
                        song = songService.create(songModel);
                    }
                    else {
                        song = songRepository.findById(songModel.getSongId()).orElse(null);
                        if (song == null) {
                            song = songService.create(songModel);
                        }

                    }
                    lSongs.add(song);
                }
                fromDB.setSingleAndEpSongs(lSongs);
            }

            return artistRepository.save(fromDB);
        }
        return null;
    }

    @Override
    public Boolean updateAlbums(Integer artistId) {
        Artist artist = artistRepository.findById(artistId).orElse(null);
        if (artist != null && artist.getAlbums() != null) {
            List<Album> lAlbums = artist.getAlbums();
            for (Album album : lAlbums) {
                albumService.setArtist(album.getAlbumId(), artistId);
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateSingleAndEpSongs(Integer artistId) {
        Artist artist = artistRepository.findById(artistId).orElse(null);
        if (artist != null && artist.getSingleAndEpSongs() != null) {
            List<Song> lSongs = artist.getSingleAndEpSongs();
            for (Song song : lSongs) {
                songService.setAlbum(song.getSongId(), artistId);
            }
            return true;
        }
        return false;
    } 
    @Override
    public void deleteById(Integer id) {
        artistRepository.deleteById(id);;
    }

    @Override
    public void deleteAll() {
        artistRepository.deleteAll();
    }


    @Override
    public ArtistModel findById(Integer id) {
        Artist fromDB = artistRepository.findById(id).orElse(null);
        if (fromDB == null) return null;

        ArtistModel artistModel = new ArtistModel(fromDB);
        return artistModel;
    }

    @Override
    public List<ArtistModel> findAll() {
        List<ArtistModel> listArtistModels = new ArrayList<>();
        for (Artist artist : artistRepository.findAll()) {
            listArtistModels.add(new ArtistModel(artist));
        }
        return listArtistModels;
    }

    @Override
    public long count() {
        return artistRepository.count();
    }

    @Override
    public List<String> getArtistNameList() {
        List<String> artistNameList = new ArrayList<>();
        for (Artist x : artistRepository.findAll()) {
            artistNameList.add(x.getArtistName());
        }
        return artistNameList;
    }

    @Override
    public ArtistModel findAlbum(Integer albumId) {
        Album album = albumRepository.findById(albumId).orElse(null);
        if (album != null) {
            for (Artist artist : artistRepository.findAll()) {
                for (Album x : artist.getAlbums()) {
                    if (x.getAlbumId().compareTo(album.getAlbumId()) == 0) {
                        return new ArtistModel(artist);
                    }
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public ArtistModel findSingleAndEpSong(Integer songId) {
        Song song = songRepository.findById(songId).orElse(null);
        if (song != null) {
            for (Artist artist : artistRepository.findAll()) {
                for (Song x : artist.getSingleAndEpSongs()) {
                    if (x.getSongId().compareTo(song.getSongId()) == 0) {
                        return new ArtistModel(artist);
                    }
                }
            }
            return null;
        }
        return null;
    }

}
