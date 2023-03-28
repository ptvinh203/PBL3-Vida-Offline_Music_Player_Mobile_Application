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

import com.pbl3.musicapplication.model.entity.Playlist;
import com.pbl3.musicapplication.model.model.PlaylistModel;
import com.pbl3.musicapplication.service.PlaylistService;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/{id}")
    public PlaylistModel findById(@PathVariable Integer id) {
        return playlistService.findById(id);
    }

    @PostMapping()
    public Playlist create(@RequestBody PlaylistModel playlistModel) {
        return playlistService.create(playlistModel);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        playlistService.delete(id);
    }

    @PutMapping("/{id}")
    public Playlist update(@PathVariable Integer id, @RequestBody PlaylistModel playlistModel) {
        return playlistService.update(id, playlistModel);
    }
}
