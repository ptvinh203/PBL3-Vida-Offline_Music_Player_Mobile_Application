package com.pbl3.musicapplication.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pbl3.musicapplication.exception.FileNotFoundException;
import com.pbl3.musicapplication.exception.FileStorageException;
import com.pbl3.musicapplication.model.entity.MyFile;
import com.pbl3.musicapplication.model.model.MyFileModel;
import com.pbl3.musicapplication.service.ArtistService;
import com.pbl3.musicapplication.service.MyFileService;
import com.pbl3.musicapplication.service.SongService;


@RestController
@RequestMapping("/file")
public class MyFileController {
    @Autowired
    private MyFileService myFileService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private SongService songService;

    @PostMapping("/uploadFile/artist/{artistId}")
    public ResponseEntity<MyFileModel> uploadArtistFile(
        @PathVariable Integer artistId, @RequestParam("file") MultipartFile multipartFile) {
        
        if (artistService.findById(artistId) != null) {
            MyFile myFile = myFileService.storeFile(multipartFile);
            artistService.setArtistImage(artistId, myFile.getFileId());
            return ResponseEntity.ok(new MyFileModel(myFile));
        }
        else return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/uploadFile/song/{songId}")
    public ResponseEntity<MyFileModel> uploadSongFile (
        @PathVariable Integer songId, @RequestParam("file") MultipartFile multipartFile) {

        if (songService.findById(songId) != null) {
            MyFile myFile = myFileService.storeFile(multipartFile);
            songService.setSongFile(songId, myFile.getFileId());
            return ResponseEntity.ok(new MyFileModel(myFile));
        }
        else return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer fileId) throws FileNotFoundException {
        MyFile myFile = myFileService.findById(fileId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(myFile.getFileType()));
        httpHeaders.setContentDisposition(ContentDisposition.builder("attachment").filename(myFile.getFileName(), StandardCharsets.UTF_8).build());

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(myFile.getFileType()))
            .headers(httpHeaders)
            .body(new ByteArrayResource(myFile.getFileData()));
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer fileId) {
        myFileService.deleteById(fileId);  
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<MyFileModel> updateFile(@PathVariable Integer fileId, @RequestParam("file") MultipartFile multipartFile) {
        try {
            MyFile myFile = myFileService.update(fileId, multipartFile);
            return ResponseEntity.ok(new MyFileModel(myFile));
        }
        catch (FileNotFoundException nfException) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        catch (FileStorageException sException) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
