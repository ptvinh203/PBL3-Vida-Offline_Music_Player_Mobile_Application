package com.pbl3.musicapplication.algorithm.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pbl3.musicapplication.algorithm.Trie;
import com.pbl3.musicapplication.algorithm.TrieNode;
import com.pbl3.musicapplication.algorithm.TrieService;

@Service
public class TrieServiceImpl implements TrieService{
    private static final String ARTIST_FILE_URL = "src/main/java/com/pbl3/musicapplication/algorithm/storage/ArtistTrieStorage.txt";
    private static final String SONG_FILE_URL = "src/main/java/com/pbl3/musicapplication/algorithm/storage/SongTrieStorage.txt";

    @Override
    public Trie buildArtistTrie() throws IOException, ClassNotFoundException{
        try {
            Trie result = new Trie();
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(ARTIST_FILE_URL)));
            result.setRoot((TrieNode)objectInputStream.readObject());
            return result;
        } catch (IOException ioe) {
            throw ioe;
        } catch (ClassNotFoundException e) {
            throw e;
        }
    }

    @Override
    public Trie buildSongTrie() throws IOException, ClassNotFoundException{
        try {
            Trie result = new Trie();
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(SONG_FILE_URL)));
            result.setRoot((TrieNode)objectInputStream.readObject());
            return result;
        } catch (IOException ioe) {
            throw ioe;
        } catch (ClassNotFoundException e) {
            throw e;
        }
    }

    @Override
    public List<String> searchArtist(Trie artistTrie, String prefix) {
        return artistTrie.autocomplete(prefix);
    }

    @Override
    public List<String> searchSong(Trie songTrie, String prefix) {
        return songTrie.autocomplete(prefix);
    }

    @Override
    public void saveToFile(Trie trie, boolean isArtist) throws IOException{
        try {
            if (isArtist) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(ARTIST_FILE_URL)));
                objectOutputStream.writeObject(trie.getRoot());
            }
            else {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(SONG_FILE_URL)));
                objectOutputStream.writeObject(trie.getRoot());
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
