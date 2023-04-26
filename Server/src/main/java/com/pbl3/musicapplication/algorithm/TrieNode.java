package com.pbl3.musicapplication.algorithm;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character, TrieNode> children;
    boolean endOfNode;

    public TrieNode() {
        children = new HashMap<>();
        endOfNode = false;  
    }
}
