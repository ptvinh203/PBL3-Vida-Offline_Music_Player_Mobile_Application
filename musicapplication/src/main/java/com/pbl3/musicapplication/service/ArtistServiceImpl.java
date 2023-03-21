package com.pbl3.musicapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService{
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Artist create(ArtistModel artistModel) {
        Artist artist = new Artist(artistModel);
        if (artist.isValid()) 
            return artistRepository.save(artist);
        return null;
    }

    @Override
    public Artist update(Integer id, ArtistModel artistModel) {
        Artist fromDB = artistRepository.findById(id).orElse(null);
        if (fromDB != null) {
            fromDB.setArtistName(artistModel.getArtistName());
            fromDB.setArtistImagePath(artistModel.getArtistImagePath());
            fromDB.setAlbums(artistModel.getAlbumModels());

            artistRepository.save(fromDB);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        artistRepository.deleteById(id);;
    }

    @Override
    public Artist findById(Integer id) {
        return artistRepository.findById(id).orElse(null);
    }

    
}
