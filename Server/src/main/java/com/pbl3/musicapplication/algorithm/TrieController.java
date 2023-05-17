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

    @GetMapping("/artist/{prefix}")
    public ResponseEntity<List<?>> artistSearch(@PathVariable String prefix) {
        return ResponseEntity.ok(trieService.search(prefix, true));
    }

    @GetMapping("/song/{prefix}")
    public ResponseEntity<List<?>> songSearch(@PathVariable String prefix) {
        return ResponseEntity.ok(trieService.search(prefix, false));
    }

    @GetMapping("/artist/all")
    public ResponseEntity<List<String>> artistSearchAll() {
        return ResponseEntity.ok(trieService.showAll(true));
    }

    @GetMapping("/song/all")
    public ResponseEntity<List<String>> songSearchAll() {
        return ResponseEntity.ok(trieService.showAll(false));
    }
}
