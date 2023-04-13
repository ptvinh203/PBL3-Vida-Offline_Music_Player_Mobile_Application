package com.pbl3.musicapplication.service.implement;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pbl3.musicapplication.exception.FileNotFoundException;
import com.pbl3.musicapplication.exception.FileStorageException;
import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.entity.MyFile;
import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.MyFileModel;
import com.pbl3.musicapplication.model.repository.ArtistRepository;
import com.pbl3.musicapplication.model.repository.MyFileRepository;
import com.pbl3.musicapplication.model.repository.SongRepository;
import com.pbl3.musicapplication.service.MyFileService;

@Service
public class MyFileServiceImpl implements MyFileService{
    @Autowired
    private MyFileRepository myFileRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository  songRepository;

    @Override
    public MyFile storeFile(MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            MyFile myFile = MyFile.builder()
                            .fileName(fileName)
                            .fileType(multipartFile.getContentType())
                            .fileData(multipartFile.getBytes())
                            .build();
                    
            if (myFile.isValid()) {
                return myFileRepository.save(myFile);
            }
            return null;
        }
        catch(IOException ioe) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ioe);
        }
    }

    @Override
    public MyFile findById(Integer fileId) {
        return myFileRepository.findById(fileId).orElseThrow(
            () -> new FileNotFoundException("File not found with id " + fileId)
        );
    }

    @Override 
    public void deleteById(Integer fileId) {
        myFileRepository.deleteById(fileId);
    }
   
    @Override
    public MyFile update(Integer fileId, MultipartFile multipartFile) {
        MyFile fromDB = this.findById(fileId);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            fromDB.setFileName(fileName);
            fromDB.setFileType(multipartFile.getContentType());
            fromDB.setFileData(multipartFile.getBytes());
                    
            if (fromDB.isValid()) {
                return myFileRepository.save(fromDB);
            }
            return null;
        }
        catch(IOException ioe) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ioe);
        }
    }

    @Override
    public MyFileModel updateArtist(Integer fileId, Integer artistId) {
        MyFile myFile = myFileRepository.findById(fileId).orElse(null);
        Artist artist = artistRepository.findById(artistId).orElse(null);
        if (myFile != null && artist != null) {
            artist.setArtistImage(myFile);
            artistRepository.save(artist);

            return new MyFileModel(myFile);
        }
        return null;
    }

    @Override
    public MyFileModel updateSong(Integer fileId, Integer songId) {
        MyFile myFile = myFileRepository.findById(fileId).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);
        if (myFile != null && song != null) {
            String[] arr = myFile.getFileType().split("/");
            if (arr[0].compareToIgnoreCase("audio") == 0) {
                song.setMusicFile(myFile);
            }
            else if (arr[0].compareToIgnoreCase("image") == 0) {
                song.setBackgroundImageFile(myFile);
            }

            songRepository.save(song);
            return new MyFileModel(myFile);
        }
        return null;
    }
}
