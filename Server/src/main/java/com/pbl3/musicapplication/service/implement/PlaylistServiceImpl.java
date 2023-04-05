package com.pbl3.musicapplication.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.model.entity.Playlist;
import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.model.PlaylistModel;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.model.repository.PlaylistRepository;
import com.pbl3.musicapplication.model.repository.SongRepository;
import com.pbl3.musicapplication.service.PlaylistService;

import jakarta.annotation.Nonnull;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    
    @Autowired 
    private PlaylistRepository playlistRepository;
    
    @Autowired
    private SongRepository songRepository;

    @Override
    public Playlist create(@Nonnull PlaylistModel playlistModel) {
        Playlist playlist = new Playlist(playlistModel);

        List<Song> tmp = new ArrayList<>();
        for (SongModel songModel : playlistModel.getSongsPlaylist()) {
            Song song = songRepository.findById(songModel.getSongId()).orElse(null);
            if (song != null) {
                tmp.add(song);
            }
        }
        playlist.setSongsPlaylist(tmp);

        if (playlist.isValid())
            return playlistRepository.save(playlist);
        return null;
    }

    @Override
    public Playlist update(Integer id, @Nonnull PlaylistModel playlistModel) {
        Playlist fromDB = playlistRepository.findById(id).orElse(null);
        if (fromDB != null) {
            fromDB.setPlaylistName(playlistModel.getPlaylistName());
            
            List<Song> tmp = new ArrayList<>();
            for (SongModel songModel : playlistModel.getSongsPlaylist()) {
                Song song = songRepository.findById(songModel.getSongId()).orElse(null);
                if (song != null && !tmp.contains(song)) {
                    tmp.add(song);
                }
            }
            fromDB.setSongsPlaylist(tmp);

            if (fromDB.isValid()) return playlistRepository.save(fromDB);
        }
        return null;
    }



    @Override
    public void deleteById(Integer id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        playlistRepository.deleteAll();
    }

    @Override
    public List<PlaylistModel> findAll() {
        List<PlaylistModel> listPlaylistModels = new ArrayList<>();
        for (Playlist playlist : playlistRepository.findAll()) {
            PlaylistModel playlistModel = new PlaylistModel(playlist);

            // List<SongModel> lSongModels = new ArrayList<>();
            // List<ArtistModel> lArtists = new ArrayList<>();
            // List<Integer> lArtistsId = new ArrayList<>();
            // for (Song song : playlist.getSongsPlaylist()) {
            //     Artist artist = artistRepository.findById(song.getArtist().getArtistId()).orElse(null);
            //     if (!lArtistsId.contains(artist.getArtistId()) && artist != null) {
            //         ArtistModel artistModel = new ArtistModel(artist);
            //         artistModel.updateAlbums();
            //         artistModel.updateSingleAndEpSongs();

            //         lArtists.add(artistModel);
            //         lArtistsId.add(artistModel.getArtistId());
            //         if (song.getAlbum() == null) {
            //             lSongModels.add(artistModel.findSongById(song.getSongId(), true));
            //         }
            //         else
            //             lSongModels.add(artistModel.findSongById(song.getSongId(), false));
            //     }
            //     else {
            //         for (ArtistModel x : lArtists) {
            //             if (x.getArtistId().compareTo(artist.getArtistId()) == 0) {
            //                 if (song.getAlbum() == null) {
            //                     lSongModels.add(x.findSongById(song.getSongId(), true));
            //                 }
            //                 else
            //                     lSongModels.add(x.findSongById(song.getSongId(), false));
            //                 break;
            //             }
            //         }

            //     }
            // }

            // playlistModel.setSongsPlaylist(lSongModels);
            listPlaylistModels.add(playlistModel);
        }
        return listPlaylistModels;
    }
    @Override
    public PlaylistModel findById(Integer id) {
        Playlist fromDB = playlistRepository.findById(id).orElse(null);
        if (fromDB == null) return null;

        PlaylistModel playlistModel = new PlaylistModel(fromDB);

        // List<SongModel> lSongModels = new ArrayList<>();
        // for (Song song : fromDB.getSongsPlaylist()) {
        //     Artist artist = artistRepository.findById(song.getArtist().getArtistId()).orElse(null);
        //     if (artist != null) {
        //         ArtistModel artistModel = new ArtistModel(artist);
        //         artistModel.updateAlbums();
        //         artistModel.updateSingleAndEpSongs();

        //         if (song.getAlbum() == null) {
        //             lSongModels.add(artistModel.findSongById(song.getSongId(), true));
        //         }
        //         else
        //             lSongModels.add(artistModel.findSongById(song.getSongId(), false));
        //     }
        // }

        // playlistModel.setSongsPlaylist(lSongModels);
        return playlistModel;
    }

    @Override
    public long count() {
        return playlistRepository.count();
    }

    @Override
    public List<SongModel> getAllSongsList(Integer id) {
        Playlist playlist = playlistRepository.findById(id).orElse(null);
        if (playlist != null && playlist.getSongsPlaylist() != null) {
            List<SongModel> listSongModels = new ArrayList<>();
            for (Song song : playlist.getSongsPlaylist()) {
                listSongModels.add(new SongModel(song));
            }
            return listSongModels;
        }
        return null;
    }

    @Override
    public List<String> getPlayListNameList() {
        List<String> playListNameList = new ArrayList<>();
        for (Playlist playlist : playlistRepository.findAll()) {
            playListNameList.add(playlist.getPlaylistName());
        }
        return playListNameList;
    }  
}
