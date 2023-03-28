package com.pbl3.musicapplication.service;

import com.pbl3.musicapplication.model.entity.Album;
import com.pbl3.musicapplication.model.model.AlbumModel;

public interface AlbumService {
    Album create(AlbumModel albumModel);
    Album update(Integer id, AlbumModel albumModel);
    void deleteById(Integer id);
    AlbumModel findById(Integer id);
}
