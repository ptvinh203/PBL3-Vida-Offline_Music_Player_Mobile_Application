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
import com.pbl3.musicapplication.model.model.SongModel;
import com.pbl3.musicapplication.service.SongService;

@Service
public class TrieServiceImpl implements TrieService {
    private static final String ARTIST_FILE_URL = "src/main/java/com/pbl3/musicapplication/algorithm/storage/ArtistTrieStorage.txt";
    private static final String SONG_FILE_URL = "src/main/java/com/pbl3/musicapplication/algorithm/storage/SongTrieStorage.txt";
    private static Trie artistTrie;
    private static Trie songTrie;

    @Autowired
    private SongService songService;

    public TrieServiceImpl() {
        try {
            artistTrie = buildArtistTrie();
        } catch (IOException | ClassNotFoundException e) {
            artistTrie = new Trie();
        }

        try {
            songTrie = buildSongTrie();
        } catch (IOException | ClassNotFoundException e) {
            songTrie = new Trie();
        }
    }

    @Override
    public Trie buildArtistTrie() throws IOException, ClassNotFoundException {
        Trie result = new Trie();
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(Paths.get(ARTIST_FILE_URL).toFile()));
        result.setRoot((TrieNode) objectInputStream.readObject());

        objectInputStream.close();
        return result;
    }

    @Override
    public Trie buildSongTrie() throws IOException, ClassNotFoundException {
        Trie result = new Trie();
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(Paths.get(SONG_FILE_URL).toFile()));
        result.setRoot((TrieNode) objectInputStream.readObject());
        objectInputStream.close();
        return result;
    }

    @Override
    public List<?> search(String prefix, boolean isArtist) {
        if (isArtist) {
            return artistTrie.autocomplete(prefix);
        } else {
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

    @Override
    public void saveToFile(boolean isArtist) throws IOException {
        if (isArtist) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(Paths.get(ARTIST_FILE_URL).toFile()));
            objectOutputStream.writeObject(artistTrie.getRoot());
            objectOutputStream.close();
        } else {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(Paths.get(SONG_FILE_URL).toFile()));
            objectOutputStream.writeObject(songTrie.getRoot());
            objectOutputStream.close();
        }
    }

    @Override
    public void insert(String newName, boolean isArtist) throws IOException {
        if (isArtist)
            artistTrie.insert(newName);
        else
            songTrie.insert(newName);

        this.saveToFile(isArtist);
    }

    @Override
    public void delete(String name, boolean isArtist) throws IOException {
        if (isArtist)
            artistTrie.delete(name);
        else
            songTrie.delete(name);

        this.saveToFile(isArtist);
    }

    @Override
    public List<String> showAll(boolean isArtist) {
        if (isArtist) {
            return artistTrie.showAll();
        } else {
            return songTrie.showAll();
        }
    }
}
