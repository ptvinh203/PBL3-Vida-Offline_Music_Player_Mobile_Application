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

import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.service.ArtistService;
import com.pbl3.musicapplication.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private SongService songService;
    @Autowired
    private ArtistService artistService;

    @GetMapping("/{id}")
    public ResponseEntity<SongModel> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(songService.findById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<SongModel>> findAll() {
        return ResponseEntity.ok(songService.findAll());
    }
    @GetMapping("/all/name")
    public ResponseEntity<List<String>>  getSongNameList() {
        return ResponseEntity.ok(songService.getSongNameList());
    }

    @PostMapping("/{artistId}")
    public ResponseEntity<SongModel> create(@PathVariable Integer artistId, @RequestBody SongModel songModel) {
        if (artistService.findById(artistId) != null) {
            Song song = songService.create(songModel);
            if (song == null) {
                return ResponseEntity.badRequest().body(null);
            }

            songService.updateArtist(artistId, song.getSongId(), true);
            return ResponseEntity.ok(new SongModel(song));
        }
        else return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        if (songService.findById(id) == null)
            return new ResponseEntity<>("Not found object", HttpStatus.NO_CONTENT);
        
        Artist artist = artistService.findSingleAndEpSong(id);
        if (artist != null) {
            songService.updateArtist(artist.getArtistId(), id, false);
        }
        songService.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongModel> update(@PathVariable Integer id, @RequestBody SongModel songModel) {
        Song song = songService.update(id, songModel);
        if (song == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new SongModel(song), HttpStatus.NO_CONTENT);
    }
}
