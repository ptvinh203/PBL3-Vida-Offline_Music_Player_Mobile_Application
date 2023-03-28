package com.pbl3.musicapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.repository.AlbumRepository;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired 
    private AlbumRepository albumRepository;

    @Override
    public Album create(AlbumModel albumModel) {
        Album album = new Album(albumModel);
        if (album.isValid())
            return albumRepository.save(album);
        return null;
    }

    @Override
    public Album update(Integer id, AlbumModel albumModel) {
        Album fromDB = albumRepository.findById(id).orElse(null);
        if (fromDB != null) {
            fromDB.setArtist(albumModel.getArtist());
            fromDB.setSongsAlbum(albumModel.getSongModels());

            albumRepository.save(fromDB);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        albumRepository.deleteById(id);
    }

    @Override
    public AlbumModel findById(Integer id) {
        Album fromDB = albumRepository.findById(id).orElse(null);
        if (fromDB == null) return null;
        return new AlbumModel(fromDB);
    }
    
}
