package com.pbl3.musicapplication.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.model.repository.ArtistRepository;
import com.pbl3.musicapplication.model.repository.SongRepository;
import com.pbl3.musicapplication.service.SongService;

import jakarta.annotation.Nonnull;

@Service
public class SongServiceImpl implements SongService{
    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;
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
            fromDB.setDownloadDate(songModel.getDownloadDate());
            fromDB.setMusicFilePath(songModel.getMusicFilePath());
            fromDB.setBackgroundImageFilePath(songModel.getBackgroundImageFilePath());
    
            if (fromDB.isValid()) return songRepository.save(fromDB);
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
        if (fromDB == null) return null;
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
        if (artist != null) {
            List<Song> list = artist.getSingleAndEpSongs();
            Song song = songRepository.findById(songId).orElse(null);
            if (song != null) {
                if (checkAdd) list.add(song);
                else list.remove(song);
    
                artist.setSingleAndEpSongs(list);
                artistRepository.save(artist);
                return true;
            }
        }
        return false;
    }
    
}
