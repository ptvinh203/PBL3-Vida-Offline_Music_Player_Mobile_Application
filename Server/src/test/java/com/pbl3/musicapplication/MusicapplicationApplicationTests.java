package com.pbl3.musicapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pbl3.musicapplication.service.SongService;

@SpringBootTest
class MusicapplicationApplicationTests {
	@Autowired
	private SongService songService;

	@Test
	void contextLoads() {
	}

	@Test
	void test1() {
		songService.setSongFile(193, 33);
	}

}
