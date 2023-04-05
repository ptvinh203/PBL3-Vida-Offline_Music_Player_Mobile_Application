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

import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.service.AlbumService;
import com.pbl3.musicapplication.service.ArtistService;
import com.pbl3.musicapplication.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private SongService songService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private AlbumService albumService;

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

    @PostMapping("/artist/{artistId}")
    public ResponseEntity<SongModel> createByArtist(@PathVariable Integer artistId, @RequestBody SongModel songModel) {
        if (artistService.findById(artistId) != null) {
            Song song = songService.create(songModel);
            if (song == null) {
                return ResponseEntity.badRequest().body(null);
            }

            songService.updateArtist(artistId, song.getSongId(), true);
            songService.setArtist(song.getSongId(), artistId);
            return ResponseEntity.ok(new SongModel(song));
        }
        else return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/album/{albumId}")
    public ResponseEntity<SongModel> createByAlbum(@PathVariable Integer albumId, @RequestBody SongModel songModel) {
        if (albumService.findById(albumId) != null) {
            Song song = songService.create(songModel);
            if (song == null) {
                return ResponseEntity.badRequest().body(null);
            }

            songService.updateAlbum(albumId, song.getSongId(), true);
            songService.setAlbum(song.getSongId(), albumId);
            songService.setArtist(song.getSongId(), albumService.findById(albumId).getArtist().getArtistId());
            return ResponseEntity.ok(new SongModel(song));
        }
        else return ResponseEntity.badRequest().body(null);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        SongModel songModel = songService.findById(id);
        ArtistModel artistModel = songService.getArtistSong(id);
        if (songModel == null || artistModel == null)
            return new ResponseEntity<>("Not found object", HttpStatus.NO_CONTENT);

        AlbumModel albumModel = songService.getAlbumSong(id);
        if (albumModel == null) 
            songService.updateArtist(artistModel.getArtistId(), id, false);
        else {
            songService.updateAlbum(albumModel.getAlbumId(), id, false);
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
