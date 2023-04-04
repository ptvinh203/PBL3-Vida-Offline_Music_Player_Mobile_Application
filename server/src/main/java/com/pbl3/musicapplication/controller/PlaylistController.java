package com.pbl3.musicapplication.controller;

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

import com.pbl3.musicapplication.model.entity.Playlist;
import com.pbl3.musicapplication.model.model.PlaylistModel;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.service.PlaylistService;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistModel> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(playlistService.findById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<PlaylistModel>> findAll() {
        return ResponseEntity.ok(playlistService.findAll());
    }
    @GetMapping("/{id}/all-songs")
    public ResponseEntity<List<SongModel>> getAllSongsList(@PathVariable Integer id) {
        return ResponseEntity.ok(playlistService.getAllSongsList(id));
    }
    @GetMapping("/all/name")
    public ResponseEntity<List<String>> getPlayListNameList() {
        return ResponseEntity.ok(playlistService.getPlayListNameList());
    }


    @PostMapping()
    public ResponseEntity<PlaylistModel> create(@RequestBody PlaylistModel playlistModel) {
        Playlist playlist = playlistService.create(playlistModel);
        if (playlist == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(new PlaylistModel(playlist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        if (playlistService.findById(id) == null) 
            return new ResponseEntity<>("Not found object", HttpStatus.NO_CONTENT);
        playlistService.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlaylistModel> update(@PathVariable Integer id, @RequestBody PlaylistModel playlistModel) {
        Playlist playlist = playlistService.update(id, playlistModel);
        if (playlist == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new PlaylistModel(playlist), HttpStatus.NO_CONTENT);
    }
}
