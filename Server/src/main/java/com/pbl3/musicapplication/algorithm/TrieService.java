package com.pbl3.musicapplication.algorithm;

import java.io.IOException;
import java.util.List;

public interface TrieService {
    Trie buildArtistTrie() throws IOException, ClassNotFoundException;
    Trie buildSongTrie() throws IOException, ClassNotFoundException;
    void insert(String newName, boolean isArtist) throws IOException;
    void delete(String name, boolean isArtist) throws IOException;
    List<String> search(String prefix, boolean isArtist);
    void saveToFile(boolean isArtist) throws IOException;
    List<String> showAll(boolean isArtist);
}
