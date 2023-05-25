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
import com.pbl3.musicapplication.algorithm.TrieType;
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
    @Autowired
    private TrieService trieService;

    @GetMapping("/{id}")
    public ResponseEntity<SongModel> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(songService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SongModel>> findAll() {
        return ResponseEntity.ok(songService.findAll());
    }

    @GetMapping("/all/name")
    public ResponseEntity<List<String>> getSongNameList() {
        return ResponseEntity.ok(songService.getSongNameList());
    }

    @GetMapping("/{id}/artist")
    public ResponseEntity<ArtistModel> getArtistOfSong(@PathVariable Integer id) {
        return ResponseEntity.ok(songService.getArtistSong(id));
    }

    @GetMapping("/search-by-name/{songName}")
    public ResponseEntity<SongModel> getSongByName(@PathVariable String songName) {
        return ResponseEntity.ok(songService.findSongByName(songName));
    }

    @PostMapping(value = "/artist/{artistId}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<SongModel> createByArtist(@PathVariable Integer artistId, @RequestBody SongModel songModel) {
        if (artistService.findById(artistId) != null) {
            Song song = songService.create(songModel);
            if (song == null) {
                return ResponseEntity.badRequest().body(null);
            }
            songService.updateArtist(artistId, song.getSongId(), true);
            songService.setArtist(song.getSongId(), artistId);

            try {
                trieService.insert(song.getSongName(), TrieType.SONG);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return ResponseEntity.ok(new SongModel(song));
        } else
            return ResponseEntity.badRequest().body(null);
    }

    @PostMapping(value = "/album/{albumId}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<SongModel> createByAlbum(@PathVariable Integer albumId, @RequestBody SongModel songModel) {
        if (albumService.findById(albumId) != null) {
            Song song = songService.create(songModel);
            if (song == null) {
                return ResponseEntity.badRequest().body(null);
            }

            songService.updateAlbum(albumId, song.getSongId(), true);
            songService.setAlbum(song.getSongId(), albumId);
            songService.setArtist(song.getSongId(), albumService.getArtistAlbum(albumId).getArtistId());

            try {
                trieService.insert(song.getSongName(), TrieType.SONG);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(new SongModel(song));
        } else
            return ResponseEntity.badRequest().body(null);
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
        songService.updatePlaylist(id, false);

        songService.deleteById(id);

        try {
            trieService.delete(songModel.getSongName(), TrieType.SONG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongModel> update(@PathVariable Integer id, @RequestBody SongModel songModel) {
        SongModel song_old = songService.findById(id);
        if (song_old == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        String songName_old = song_old.getSongName();

        Song song = songService.update(id, songModel);
        if (song == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (songName_old.compareTo(song.getSongName()) != 0) {
            try {
                trieService.delete(songName_old, TrieType.SONG);
                trieService.insert(song.getSongName(), TrieType.SONG);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(new SongModel(song), HttpStatus.NO_CONTENT);
    }
}
