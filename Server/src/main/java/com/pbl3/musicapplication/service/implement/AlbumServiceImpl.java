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
import com.pbl3.musicapplication.service.SongService;

import jakarta.annotation.Nonnull;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongService songService;

    @Override
    public Album create(@Nonnull AlbumModel albumModel) {
        Album album = new Album(albumModel);
        if (!album.isValid())
            return null;

        if (albumModel.getSongsAlbum() != null) {
            List<Song> lSongs = new ArrayList<>();
            for (SongModel songModel : albumModel.getSongsAlbum()) {
                Song song;
                if (songModel.getSongId() == null) {
                    song = songService.create(songModel);
                } else {
                    song = songRepository.findById(songModel.getSongId()).orElse(null);
                    if (song == null) {
                        song = songService.create(songModel);
                    }
                }
                lSongs.add(song);
            }
            album.setSongsAlbum(lSongs);
        }
        Album result = albumRepository.save(album);
        return result;
    }

    @Override
    public Album update(Integer id, @Nonnull AlbumModel albumModel) {
        Album fromDB = albumRepository.findById(id).orElse(null);
        if (fromDB != null) {
            fromDB.setAlbumName(albumModel.getAlbumName());
            if (!fromDB.isValid())
                return null;

            if (albumModel.getSongsAlbum() != null) {
                List<Song> lSongs = new ArrayList<>();
                for (SongModel songModel : albumModel.getSongsAlbum()) {
                    Song song;
                    if (songModel.getSongId() == null) {
                        song = songService.create(songModel);
                    } else {
                        song = songRepository.findById(songModel.getSongId()).orElse(null);
                        if (song == null) {
                            song = songService.create(songModel);
                        }
                    }
                    lSongs.add(song);
                }
                fromDB.setSongsAlbum(lSongs);
            }

            return albumRepository.save(fromDB);
        }
        return null;
    }

    @Override
    public AlbumModel setArtist(Integer albumId, Integer artistId) {
        Artist artist = artistRepository.findById(artistId).orElse(null);
        Album album = albumRepository.findById(albumId).orElse(null);
        if (artist != null && album != null) {
            album.setArtist(artist);
            return new AlbumModel(albumRepository.save(album));
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        albumRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        albumRepository.deleteAll();
    }

    @Override
    public AlbumModel findById(Integer id) {
        Album fromDB = albumRepository.findById(id).orElse(null);
        if (fromDB == null) {
            return null;
        }
        return new AlbumModel(fromDB);
    }

    @Override
    public List<AlbumModel> findAll() {
        List<AlbumModel> listAlbumModels = new ArrayList<>();
        for (Album album : albumRepository.findAll()) {
            listAlbumModels.add(new AlbumModel(album));
        }
        return listAlbumModels;
    }

    @Override
    public long count() {
        return albumRepository.count();
    }

    @Override
    public List<String> getAlbumNameList() {
        List<String> albumNameList = new ArrayList<>();
        for (Album album : albumRepository.findAll()) {
            albumNameList.add(album.getAlbumName());
        }
        return albumNameList;
    }

    @Override
    public List<SongModel> getAllSongsList(Integer id) {
        Album album = albumRepository.findById(id).orElse(null);
        if (album != null && album.getSongsAlbum() != null) {
            List<SongModel> listSongModels = new ArrayList<>();
            for (Song song : album.getSongsAlbum()) {
                listSongModels.add(new SongModel(song));
            }
            return listSongModels;
        }
        return null;
    }

    @Override
    public Boolean updateArtist(Integer artistId, Integer albumId, Boolean checkAdd) {
        Artist artist = artistRepository.findById(artistId).orElse(null);
        Album album = albumRepository.findById(albumId).orElse(null);
        if (artist != null && album != null) {
            List<Album> list = artist.getAlbums();
            boolean contains = false;
            for (Album tmp : list) {
                if (tmp.getAlbumId().compareTo(albumId) == 0) {
                    if (!checkAdd)
                        list.remove(tmp);
                    contains = true;

                    List<Song> lSongs = artist.getSingleAndEpSongs();
                    for (Song song : album.getSongsAlbum()) {
                        Song tmp1 = songRepository.findById(song.getSongId()).orElse(null);
                        if (tmp1 != null) {
                            lSongs.add(tmp1);
                        }
                    }
                    artist.setSingleAndEpSongs(lSongs);
                    break;
                }
            }
            if (!contains && checkAdd)
                list.add(album);

            artist.setAlbums(list);
            artistRepository.save(artist);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateSongs(Integer albumId, Boolean checkAdd) {
        Album album = albumRepository.findById(albumId).orElse(null);
        if (album != null && album.getSongsAlbum() != null) {
            List<Song> songs = album.getSongsAlbum();
            for (Song song : songs) {
                if (checkAdd) {
                    songService.setAlbum(song.getSongId(), albumId);
                    songService.setArtist(song.getSongId(), album.getArtist().getArtistId());
                } else
                    songService.removeAlbum(song.getSongId());
            }
            return true;
        }
        return false;
    }

    @Override
    public ArtistModel getArtistAlbum(Integer albumId) {
        Album album = albumRepository.findById(albumId).orElse(null);
        if (album != null && album.getArtist() != null) {
            Artist artist = artistRepository.findById(album.getArtist().getArtistId()).orElse(null);
            if (artist != null) {
                return new ArtistModel(artist);
            }
        }
        return null;
    }
}
