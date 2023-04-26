package com.pbl3.musicapplication.algorithm;

import java.io.IOException;
import java.util.List;

public interface TrieService {
    Trie buildArtistTrie() throws IOException, ClassNotFoundException;
    Trie buildSongTrie() throws IOException, ClassNotFoundException;
    List<String> searchArtist(Trie trie, String prefix);
    List<String> searchSong(Trie trie, String prefix);
    void saveToFile(Trie trie, boolean isArtist) throws IOException;
}
