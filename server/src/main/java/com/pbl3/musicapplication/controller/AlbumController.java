package com.pbl3.musicapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.service.AlbumService;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/{id}")
    public AlbumModel findById(@PathVariable Integer id) {
        return albumService.findById(id);
    }

    @PostMapping()
    public Album create(@RequestBody AlbumModel albumModel) {
        return albumService.create(albumModel);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        albumService.deleteById(id);
    }

    @PostMapping("/{id}") 
    public Album update(@PathVariable Integer id, @RequestBody AlbumModel albumModel) {
        return albumService.update(id, albumModel);
    }
}
