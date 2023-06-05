package com.pbl3.musicapplication.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class TrieController {

    @Autowired
    private TrieService trieService;

    @GetMapping(value = "/artist/{prefix}")
    public ResponseEntity<List<?>> artistSearch(@PathVariable String prefix) {
        return ResponseEntity.ok(trieService.search(prefix, TrieType.ARTIST));
    }

    @GetMapping(value = "/song/{prefix}")
    public ResponseEntity<List<?>> songSearch(@PathVariable String prefix) {
        return ResponseEntity.ok(trieService.search(prefix, TrieType.SONG));
    }

    @GetMapping(value = "/album/{prefix}")
    public ResponseEntity<List<?>> albumSearch(@PathVariable String prefix) {
        return ResponseEntity.ok(trieService.search(prefix, TrieType.ALBUM));
    }

    @GetMapping("/artist/all")
    public ResponseEntity<Map<List<String>, Integer>> artistSearchAll() {
        Map<List<String>, Integer> ans = new HashMap<>();
        List<String> listSong = trieService.showAll(TrieType.ARTIST);
        ans.put(listSong, listSong.size());
        return ResponseEntity.ok(ans);
    }

    @GetMapping("/song/all")
    public ResponseEntity<Map<List<String>, Integer>> songSearchAll() {
        Map<List<String>, Integer> ans = new HashMap<>();
        List<String> listSong = trieService.showAll(TrieType.SONG);
        ans.put(listSong, listSong.size());
        return ResponseEntity.ok(ans);
    }

    @GetMapping("/album/all")
    public ResponseEntity<Map<List<String>, Integer>> albumSearchAll() {
        Map<List<String>, Integer> ans = new HashMap<>();
        List<String> listSong = trieService.showAll(TrieType.ALBUM);
        ans.put(listSong, listSong.size());
        return ResponseEntity.ok(ans);
    }
}
