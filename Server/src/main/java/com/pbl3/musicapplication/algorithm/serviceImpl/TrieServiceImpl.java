package com.pbl3.musicapplication.algorithm.serviceImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.algorithm.Trie;
import com.pbl3.musicapplication.algorithm.TrieNode;
import com.pbl3.musicapplication.algorithm.TrieService;
import com.pbl3.musicapplication.algorithm.TrieType;
import com.pbl3.musicapplication.model.model.AlbumModel;
import com.pbl3.musicapplication.model.model.ArtistModel;
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.service.AlbumService;
import com.pbl3.musicapplication.service.ArtistService;
import com.pbl3.musicapplication.service.SongService;

@Service
public class TrieServiceImpl implements TrieService {
    private static final String ARTIST_FILE_URL = "src/main/java/com/pbl3/musicapplication/algorithm/storage/ArtistTrieStorage.txt";
    private static final String SONG_FILE_URL = "src/main/java/com/pbl3/musicapplication/algorithm/storage/SongTrieStorage.txt";
    private static final String ALBUM_FILE_URL = "src/main/java/com/pbl3/musicapplication/algorithm/storage/AlbumTrieStorage.txt";
    private static Trie artistTrie;
    private static Trie songTrie;
    private static Trie albumTrie;

    @Autowired
    private SongService songService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private AlbumService albumService;

    public TrieServiceImpl() {
        try {
            artistTrie = buildTrie(TrieType.ARTIST);
        } catch (IOException | ClassNotFoundException e) {
            artistTrie = new Trie();
        }

        try {
            songTrie = buildTrie(TrieType.SONG);
        } catch (IOException | ClassNotFoundException e) {
            songTrie = new Trie();
        }

        try {
            albumTrie = buildTrie(TrieType.ALBUM);
        } catch (IOException | ClassNotFoundException e) {
            albumTrie = new Trie();
        }
    }

    @Override
    public Trie buildTrie(TrieType trieType) throws IOException, ClassNotFoundException {
        Trie result = new Trie();
        switch (trieType) {
            case ARTIST: {
                ObjectInputStream objectInputStream = new ObjectInputStream(
                        new FileInputStream(Paths.get(ARTIST_FILE_URL).toFile()));
                result.setRoot((TrieNode) objectInputStream.readObject());

                objectInputStream.close();
                break;
            }
            case ALBUM: {
                ObjectInputStream objectInputStream = new ObjectInputStream(
                        new FileInputStream(Paths.get(ALBUM_FILE_URL).toFile()));

                result.setRoot((TrieNode) objectInputStream.readObject());

                objectInputStream.close();
                break;
            }
            case SONG: {
                ObjectInputStream objectInputStream = new ObjectInputStream(
                        new FileInputStream(Paths.get(SONG_FILE_URL).toFile()));
                result.setRoot((TrieNode) objectInputStream.readObject());

                objectInputStream.close();
                break;
            }
        }
        return result;
    }

    @Override
    public List<?> search(String prefix, TrieType trieType) {
        switch (trieType) {
            case ARTIST: {
                List<String> listArtistName = artistTrie.autocomplete(prefix);
                List<ArtistModel> result = new ArrayList<>();
                for (String artistName : listArtistName) {
                    ArtistModel artistModel = artistService.findByName(artistName);
                    if (artistModel != null) {
                        result.add(artistModel);
                    }
                }
                return result;
            }
            case ALBUM: {
                List<String> listAlbumName = albumTrie.autocomplete(prefix);
                List<AlbumModel> result = new ArrayList<>();
                for (String albumName : listAlbumName) {
                    AlbumModel albumModel = albumService.findAlbumByName(albumName);
                    if (albumModel != null) {
                        result.add(albumModel);
                    }
                }
                return result;
            }
            case SONG: {
                List<String> listSongName = songTrie.autocomplete(prefix);
                List<SongModel> result = new ArrayList<>();
                for (String songName : listSongName) {
                    SongModel songModel = songService.findSongByName(songName);
                    if (songModel != null) {
                        result.add(songModel);
                    }
                }
                return result;
            }
        }
        return null;
    }

    @Override
    public void saveToFile(TrieType trieType) throws IOException {
        switch (trieType) {
            case ARTIST: {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                        new FileOutputStream(Paths.get(ARTIST_FILE_URL).toFile()));
                objectOutputStream.writeObject(artistTrie.getRoot());
                objectOutputStream.close();
                break;
            }
            case ALBUM: {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                        new FileOutputStream(Paths.get(ALBUM_FILE_URL).toFile()));
                objectOutputStream.writeObject(albumTrie.getRoot());
                objectOutputStream.close();
                break;
            }
            case SONG: {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                        new FileOutputStream(Paths.get(SONG_FILE_URL).toFile()));
                objectOutputStream.writeObject(songTrie.getRoot());
                objectOutputStream.close();
                break;
            }
        }
    }

    @Override
    public void insert(String newName, TrieType trieType) throws IOException {
        switch (trieType) {
            case ARTIST: {
                artistTrie.insert(newName);
                break;
            }
            case ALBUM: {
                albumTrie.insert(newName);
                break;
            }
            case SONG: {
                songTrie.insert(newName);
                break;
            }
        }
        this.saveToFile(trieType);
    }

    @Override
    public void delete(String name, TrieType trieType) throws IOException {
        switch (trieType) {
            case ARTIST: {
                artistTrie.delete(name);
                break;
            }
            case ALBUM: {
                albumTrie.delete(name);
                break;
            }
            case SONG: {
                songTrie.delete(name);
                break;
            }
        }
        this.saveToFile(trieType);
    }

    @Override
    public List<String> showAll(TrieType trieType) {
        switch (trieType) {
            case ARTIST: {
                return artistTrie.showAll();
            }
            case ALBUM: {
                return albumTrie.showAll();
            }
            case SONG: {
                return songTrie.showAll();
            }
        }
        return null;
    }
}
