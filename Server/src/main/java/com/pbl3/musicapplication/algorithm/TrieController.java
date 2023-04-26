package com.pbl3.musicapplication.algorithm;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class TrieController {
    private static Trie artistTrie;
    private static Trie songTrie;

    @Autowired
    private TrieService trieService;

    @GetMapping("/artist") 
    public ResponseEntity<List<String>> artistSearch(@RequestParam String prefix ) {
        if (artistTrie == null) {
            try {
                artistTrie = trieService.buildArtistTrie();
            } catch (ClassNotFoundException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }
        return null;
    }

    @GetMapping("/song") 
    public ResponseEntity<List<String>> songSearch(@RequestParam String prefix ) {
        if (songTrie == null) {
            try {
                songTrie = trieService.buildSongTrie();
            } catch (ClassNotFoundException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    
}
