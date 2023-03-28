package com.pbl3.musicapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.model.entity.Playlist;
import com.pbl3.musicapplication.model.model.PlaylistModel;
import com.pbl3.musicapplication.model.repository.PlaylistRepository;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    
    @Autowired 
    private PlaylistRepository playlistRepository;

    @Override
    public Playlist create(PlaylistModel playlistModel) {
        return playlistRepository.save(new Playlist(playlistModel));
    }

    @Override
    public Playlist update(Integer id, PlaylistModel playlistModel) {
        Playlist fromDB = playlistRepository.findById(id).orElse(null);
        if (fromDB != null) {
            fromDB.setSongsPList(playlistModel.getSongModels());

            playlistRepository.save(fromDB);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public PlaylistModel findById(Integer id) {
        Playlist fromDB = playlistRepository.findById(id).orElse(null);
        if (fromDB == null) return null;
        return new PlaylistModel(fromDB);
    }  
}
