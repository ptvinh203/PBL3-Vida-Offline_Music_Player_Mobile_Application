package com.pbl3.musicapplication.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbl3.musicapplication.algorithm.TrieService;
import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.service.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private TrieService trieService;

    @GetMapping("/{id}")
    public ResponseEntity<ArtistModel> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(artistService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArtistModel>> findAll() {
        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping("/all/name")
    public ResponseEntity<List<String>> getArtistNameList() {
        return ResponseEntity.ok(artistService.getArtistNameList());
    }

    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ArtistModel> create(@RequestBody ArtistModel artistModel) {
        Artist artist = artistService.create(artistModel);
        if (artist == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // artistService.updateAlbums(artist.getArtistId());
        // artistService.updateSingleAndEpSongs(artist.getArtistId());

        try {
            trieService.insert(artist.getArtistName(), true);
        } catch (IOException e) {
        }

        return ResponseEntity.ok(new ArtistModel(artist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        ArtistModel artistModel = artistService.findById(id);
        if (artistModel == null)
            return new ResponseEntity<>("Not found object", HttpStatus.NO_CONTENT);
        artistService.deleteById(id);
        try {
            trieService.delete(artistModel.getArtistName(), true);
        } catch (IOException e) {
        }
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistModel> update(@PathVariable Integer id, @RequestBody ArtistModel artistModel) {
        Artist artist = artistService.update(id, artistModel);
        if (artist == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ArtistModel(artist), HttpStatus.NO_CONTENT);
    }

}
