package com.pbl3.musicapplication.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.entity.MyFile;
import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.model.repository.AlbumRepository;
import com.pbl3.musicapplication.model.repository.ArtistRepository;
import com.pbl3.musicapplication.model.repository.MyFileRepository;
import com.pbl3.musicapplication.model.repository.SongRepository;
import com.pbl3.musicapplication.service.SongService;

import jakarta.annotation.Nonnull;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private MyFileRepository myFileRepository;

    @Override
    public Song create(@Nonnull SongModel songModel) {
        Song song = new Song(songModel);
        if (song.isValid())
            return songRepository.save(song);
        return null;
    }

    @Override
    public Song update(Integer id, @Nonnull SongModel songModel) {
        Song fromDB = songRepository.findById(id).orElse(null);
        if (fromDB != null) {
            fromDB.setSongName(songModel.getSongName());

            if (fromDB.isValid())
                return songRepository.save(fromDB);
        }
        return null;
    }

    @Override
    public SongModel setArtist(Integer songId, Integer artistId) {
        Artist artist = artistRepository.findById(artistId).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);
        if (artist != null && song != null) {
            song.setArtist(artist);
            return new SongModel(songRepository.save(song));
        }
        return null;
    }

    @Override
    public SongModel setAlbum(Integer songId, Integer albumId) {
        Album album = albumRepository.findById(albumId).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);
        if (album != null && song != null) {
            song.setAlbum(album);
            return new SongModel(songRepository.save(song));
        }
        return null;
    }

    @Override
    public SongModel setSongFile(Integer songId, Integer fileId) {
        Song song = songRepository.findById(songId).orElse(null);
        MyFile myFile = myFileRepository.findById(fileId).orElse(null);
        if (song != null && myFile != null) {
            String[] arr = myFile.getFileType().split("/");
            if (arr[0].compareToIgnoreCase("audio") == 0) {
                song.setMusicFile(myFile);
            } else if (arr[0].compareToIgnoreCase("image") == 0) {
                song.setBackgroundImageFile(myFile);
            }
            return new SongModel(songRepository.save(song));
        }
        return null;
    }

    @Override
    public SongModel removeAlbum(Integer songId) {
        Song song = songRepository.findById(songId).orElse(null);
        if (song != null) {
            song.setAlbum(null);
            return new SongModel(songRepository.save(song));
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        songRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        songRepository.deleteAll();
    }

    @Override
    public SongModel findById(Integer id) {
        Song fromDB = songRepository.findById(id).orElse(null);
        if (fromDB == null)
            return null;

        return new SongModel(fromDB);
    }

    @Override
    public List<String> getSongNameList() {
        List<String> songNameList = new ArrayList<>();
        for (Song x : songRepository.findAll()) {
            songNameList.add(x.getSongName());
        }
        return songNameList;
    }

    @Override
    public List<SongModel> findAll() {
        List<SongModel> tmp = new ArrayList<>();
        for (Song song : songRepository.findAll()) {
            tmp.add(new SongModel(song));
        }
        return tmp;
    }

    @Override
    public long count() {
        return songRepository.count();
    }

    @Override
    public Boolean updateArtist(Integer artistId, Integer songId, Boolean checkAdd) {
        Artist artist = artistRepository.findById(artistId).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);
        if (artist != null && song != null) {
            List<Song> list = artist.getSingleAndEpSongs();
            boolean contains = false;
            for (Song tmp : list) {
                if (tmp.getSongId().compareTo(songId) == 0) {
                    if (!checkAdd)
                        list.remove(tmp);
                    contains = true;
                    break;
                }
            }
            if (!contains && checkAdd)
                list.add(song);

            artist.setSingleAndEpSongs(list);
            artistRepository.save(artist);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateAlbum(Integer albumId, Integer songId, Boolean checkAdd) {
        Album album = albumRepository.findById(albumId).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);
        if (album != null && song != null) {
            List<Song> list = album.getSongsAlbum();
            boolean contains = false;
            for (Song tmp : list) {
                if (tmp.getSongId().compareTo(songId) == 0) {
                    if (!checkAdd)
                        list.remove(tmp);
                    contains = true;
                    break;
                }
            }
            if (!contains && checkAdd)
                list.add(song);

            album.setSongsAlbum(list);
            albumRepository.save(album);
            return true;
        }
        return false;
    }

    @Override
    public ArtistModel getArtistSong(Integer songId) {
        Song song = songRepository.findById(songId).orElse(null);
        if (song != null && song.getArtist() != null) {
            Artist artist = artistRepository.findById(song.getArtist().getArtistId()).orElse(null);
            if (artist != null) {
                return new ArtistModel(artist);
            }
        }
        return null;
    }

    @Override
    public AlbumModel getAlbumSong(Integer songId) {
        Song song = songRepository.findById(songId).orElse(null);
        if (song != null && song.getAlbum() != null) {
            Album album = albumRepository.findById(song.getAlbum().getAlbumId()).orElse(null);
            if (album != null) {
                return new AlbumModel(album);
            }
        }
        return null;
    }

    @Override
    public SongModel findSongByName(String songName) {
        for (Song song : songRepository.findAll()) {
            if (song.getSongName().compareTo(songName) == 0) {
                return new SongModel(song);
            }
        }
        return null;
    }

}
