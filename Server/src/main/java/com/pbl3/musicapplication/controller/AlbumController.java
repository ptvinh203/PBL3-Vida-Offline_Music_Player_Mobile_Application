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
import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.service.AlbumService;
import com.pbl3.musicapplication.service.ArtistService;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private TrieService trieService;

    @GetMapping("/{id}")
    public ResponseEntity<AlbumModel> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(albumService.findById(id));
    }

    @GetMapping("/{id}/all-songs")
    public ResponseEntity<List<SongModel>> getAllSongsList(@PathVariable Integer id) {
        return ResponseEntity.ok(albumService.getAllSongsList(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AlbumModel>> findAll() {
        return ResponseEntity.ok(albumService.findAll());
    }

    @GetMapping("/all/name")
    public ResponseEntity<List<String>> getAlbumNameList() {
        return ResponseEntity.ok(albumService.getAlbumNameList());
    }

    @PostMapping(value = "/{artistId}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public ResponseEntity<AlbumModel> create(@PathVariable Integer artistId, @RequestBody AlbumModel albumModel) {
        if (artistService.findById(artistId) != null) {
            Album album = albumService.create(albumModel);
            if (album == null) {
                return ResponseEntity.badRequest().body(null);
            }

            albumService.updateArtist(artistId, album.getAlbumId(), true);
            albumService.updateSongs(album.getAlbumId(), true);
            albumService.setArtist(album.getAlbumId(), artistId);

            try {
                trieService.insert(album.getAlbumName(), TrieType.ALBUM);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return ResponseEntity.ok(new AlbumModel(album));
        } else
            return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        AlbumModel albumModel = albumService.findById(id);
        ArtistModel artistModel = albumService.getArtistAlbum(id);
        if (albumModel == null || artistModel == null) {
            return new ResponseEntity<>("Not found object", HttpStatus.NO_CONTENT);
        }

        albumService.updateArtist(artistModel.getArtistId(), id, false);
        albumService.updateSongs(id, false);
        albumService.deleteById(id);

        try {
            trieService.delete(albumModel.getAlbumName(), TrieType.ALBUM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumModel> update(@PathVariable Integer id, @RequestBody AlbumModel albumModel) {
        AlbumModel album_old = albumService.findById(id);
        if (album_old == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        String albumName_old = album_old.getAlbumName();

        Album album = albumService.update(id, albumModel);
        if (album == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (albumName_old.compareTo(album.getAlbumName()) != 0) {
            try {
                trieService.delete(albumName_old, TrieType.ALBUM);
                trieService.insert(album.getAlbumName(), TrieType.ALBUM);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(new AlbumModel(album), HttpStatus.NO_CONTENT);
    }
}
