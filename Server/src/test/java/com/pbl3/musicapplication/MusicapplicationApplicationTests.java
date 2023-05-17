package com.pbl3.musicapplication;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pbl3.musicapplication.algorithm.TrieService;

@SpringBootTest
class MusicapplicationApplicationTests {

	@Autowired
	private TrieService trieService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testTrieService() throws IOException {
		trieService.insert("Anh đã ổn hơn", false);

	}

}
