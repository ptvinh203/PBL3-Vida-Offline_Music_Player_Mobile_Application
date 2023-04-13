package com.pbl3.musicapplication.service;

import org.springframework.web.multipart.MultipartFile;

import com.pbl3.musicapplication.exception.FileNotFoundException;
import com.pbl3.musicapplication.exception.FileStorageException;
import com.pbl3.musicapplication.model.entity.MyFile;
import com.pbl3.musicapplication.model.model.MyFileModel;

public interface MyFileService {
    MyFile storeFile(MultipartFile multipartFile);
    MyFile findById(Integer fileId) throws FileNotFoundException;
    void deleteById(Integer fileId);
    MyFile update(Integer fileId, MultipartFile multipartFile) throws FileNotFoundException, FileStorageException;
    MyFileModel updateArtist(Integer fileId, Integer artistId);
    MyFileModel updateSong(Integer fileId, Integer songId);
}
