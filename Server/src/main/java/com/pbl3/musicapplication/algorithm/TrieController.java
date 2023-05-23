package com.pbl3.musicapplication.algorithm;

import java.util.List;

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
    public ResponseEntity<List<String>> artistSearchAll() {
        return ResponseEntity.ok(trieService.showAll(TrieType.ARTIST));
    }

    @GetMapping("/song/all")
    public ResponseEntity<List<String>> songSearchAll() {
        return ResponseEntity.ok(trieService.showAll(TrieType.SONG));
    }

    @GetMapping("/album/all")
    public ResponseEntity<List<String>> albumSearchAll() {
        return ResponseEntity.ok(trieService.showAll(TrieType.ALBUM));
    }
}
