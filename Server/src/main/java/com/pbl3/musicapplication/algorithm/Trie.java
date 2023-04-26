package com.pbl3.musicapplication.algorithm;


import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = this.root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!current.children.containsKey(ch)) {
                current.children.put(ch, new TrieNode());
            }
            current = current.children.get(ch);
        }
        current.endOfNode = true;
    }

    /*
      Search to check if the word exists in the Trie or not
      Iterative implementation of search into trie
    */
    public boolean search(String word) {
        TrieNode current = this.root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) return false;
        }
        return current.endOfNode;
    }

    public void delete(String word) {
        delete(root, word, 0);
    }
    //return true if parent node should delete the mapping
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            //when end-of-word is reached, only delete if current.endOfNode is true
            if (!current.endOfNode) {
                return false;
            }
            current.endOfNode = false;
            // if current has no other mapping then return true
            return (current.children.size() == 0);
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word,  index + 1);

        //if true is returned then delete the mapping of character and TrieNode reference from map
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            //return true if no mappings are left in the map
            return (current.children.size() == 0);
        }
        return false;
    }

    public boolean startWith(String prefix) {
        TrieNode current = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return true;
    }

    //autocomplete feature
    public List<String> autocomplete(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode current = this.root;
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return result;
            }
            current = current.children.get(ch);
        }

        autocompleteHelper(current, new StringBuilder(prefix), result);
        return result;    
    }
    private void autocompleteHelper(TrieNode current, StringBuilder sb, List<String> result) {
        if (current.endOfNode) {
            result.add(sb.toString());
        }

        for (char ch : current.children.keySet()) {
            sb.append(ch);
            autocompleteHelper(current.children.get(ch), sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
