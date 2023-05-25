package com.pbl3.musicapplication.algorithm;

import java.io.IOException;
import java.util.List;

public interface TrieService {
    Trie buildTrie(TrieType trieType) throws IOException, ClassNotFoundException;

    void insert(String newName, TrieType trieType) throws IOException;

    void delete(String name, TrieType trieType) throws IOException;

    List<?> search(String prefix, TrieType trieType);

    void saveToFile(TrieType trieType) throws IOException;

    List<String> showAll(TrieType trieType);
}
