package com.pbl3.musicapplication.algorithm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class TrieNode implements Serializable {
    private static final long serialVersionUID = 1234567L;

    Map<Character, TrieNode> children;
    boolean endOfNode;

    public TrieNode() {
        children = new HashMap<>();
        endOfNode = false;  
    }
}
