package com.pbl3.musicapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.service.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @GetMapping("/{id}")
    public ArtistModel findById(@PathVariable Integer id) {
        return artistService.findById(id);
    }

    @PostMapping() 
    public Artist create(@RequestBody ArtistModel artistModel) {
        return artistService.create(artistModel);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        artistService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Artist update(@PathVariable Integer id, @RequestBody ArtistModel artistModel) {
        return artistService.update(id, artistModel);
    }
}
