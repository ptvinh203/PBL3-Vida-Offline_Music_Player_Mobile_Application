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
        if (artist.isValid()) 
            return artistRepository.save(artist);
        return null;
    }

    @Override
    public Artist update(Integer id, @Nonnull ArtistModel artistModel) {
        Artist fromDB = artistRepository.findById(id).orElse(null);
        if (fromDB != null) {
            fromDB.setArtistName(artistModel.getArtistName());
            fromDB.setArtistImagePath(artistModel.getArtistImagePath());
            
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

            if (fromDB.isValid()) return artistRepository.save(fromDB);
        }
        return null;
    }

    @Override
    public Artist updateAlbums(Integer id, Boolean checkAdd, @Nonnull Album album) {
        Artist artist = artistRepository.findById(id).orElse(null);
        if (artist != null) {
            List<Album> list = artist.getAlbums();
            if (checkAdd) list.add(album);
            else list.remove(album);

            artist.setAlbums(list);
            return artistRepository.save(artist);
        }
        return null;
    }

    @Override
    public Artist updateSingleAndEpSongs(Integer id, Boolean checkAdd, @Nonnull Song song) {
        Artist artist = artistRepository.findById(id).orElse(null);
        if (artist != null) {
            List<Song> list = artist.getSingleAndEpSongs();
            if (checkAdd) list.add(song);
            else list.remove(song);

            artist.setSingleAndEpSongs(list);
            return artistRepository.save(artist);
        }   
        return null;
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
        return new ArtistModel(fromDB);
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
    public Artist findAlbum(Integer albumId) {
        Album album = albumRepository.findById(albumId).orElse(null);
        if (album != null) {
            for (Artist artist : artistRepository.findAll()) {
                for (Album x : artist.getAlbums()) {
                    if (x.getAlbumId().compareTo(album.getAlbumId()) == 0) {
                        return artist;
                    }
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public Artist findSingleAndEpSong(Integer songId) {
        Song song = songRepository.findById(songId).orElse(null);
        if (song != null) {
            for (Artist artist : artistRepository.findAll()) {
                for (Song x : artist.getSingleAndEpSongs()) {
                    if (x.getSongId().compareTo(song.getSongId()) == 0) {
                        return artist;
                    }
                }
            }
            return null;
        }
        return null;
    }

}
