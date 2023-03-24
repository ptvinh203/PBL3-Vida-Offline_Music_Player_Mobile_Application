package com.pbl3.musicapplication.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.service.SongService;

@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/testCreate")
    public Song test() {
        return null;
    }

    @GetMapping("/{id}")
    public Song findById(@PathVariable Integer id) {
        return songService.findById(id);
    }

    @PostMapping
    public Song create(@RequestBody SongModel songModel) {
        return songService.create(songModel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        songService.delete(id);
    }

    @PutMapping("/{id}")
    public Song update(@PathVariable Integer id, @RequestBody SongModel songModel) {
        return songService.update(id, songModel);
    }
}
