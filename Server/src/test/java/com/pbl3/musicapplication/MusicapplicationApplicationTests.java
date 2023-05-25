package com.pbl3.musicapplication;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pbl3.musicapplication.algorithm.TrieService;
import com.pbl3.musicapplication.algorithm.TrieType;

@SpringBootTest
class MusicapplicationApplicationTests {

	@Autowired
	private TrieService trieService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testTrieService() throws IOException {
		// trieService.insert("99%", TrieType.ALBUM);
		// trieService.insert("album4", TrieType.ALBUM);
		// for (String artistName : trieService.showAll(TrieType.ARTIST)) {
		// trieService.delete(artistName, TrieType.ALBUM);
		// }

		// for (String name : trieService.showAll(TrieType.ALBUM)) {
		// System.out.println(name);
		// }
	}

}
