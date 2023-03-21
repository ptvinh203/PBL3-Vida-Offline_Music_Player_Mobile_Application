package com.pbl3.musicapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.model.repository.SongRepository;

@Service
public class SongServiceImpl implements SongService{
    @Autowired
    private SongRepository songRepository;

    @Override
    public Song create(SongModel songModel) {
        Song song = new Song(songModel);
        if (song.isValid())
            return songRepository.save(song);
        return null;
    }

    @Override
    public Song update(Integer id, SongModel songModel) {
        Song fromDB = songRepository.findById(id).orElse(null);
        if (fromDB == null) {
            return null;
        }
        fromDB.setSongName(songModel.getSongName());
        fromDB.setArtist(songModel.getArtistModel());
        fromDB.setDownloadDate(songModel.getDownloadDate());
        fromDB.setMusicFilePath(songModel.getMusicFilePath());
        fromDB.setBackgroundImageFilePath(songModel.getBackgroundImageFilePath());

        return songRepository.save(fromDB);
    }

    @Override
    public void delete(Integer id) {
        songRepository.deleteById(id);
    }

    @Override
    public Song findById(Integer id) {
        return songRepository.findById(id).orElse(null);
    }
    
}
