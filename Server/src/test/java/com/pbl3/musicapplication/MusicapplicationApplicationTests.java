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
		// trieService.insert("00 (Intro)", false);
		// trieService.insert("Chìm Sâu", false);
		// trieService.insert("Suit & Tie", false);
		// trieService.insert("Va Vào Giai Điệu Này", false);
		// trieService.insert("Tối Nay Ta Đi Đâu Nhờ", false);
		// trieService.insert("Chỉ Một Đêm Nữa Thôi", false);
		// trieService.insert("Thôi Em Đừng Đi", false);
		// trieService.insert("50/50 (Interlude)", false);
		// trieService.insert("Cuốn Cho Anh Một Điếu Nữa Đi", false);
		// trieService.insert("Show Me Love", false);
		// trieService.insert("Tại Vì Sao", false);
		// trieService.insert("Thờ Er", false);
		// trieService.insert("Ai Mới Là Kẻ Xấu Xa", false);
		// trieService.insert("Badtrip", false);
		// trieService.insert("99", false);

		// trieService.delete("song123", false);
		// trieService.insert("song123456", false);
		// trieService.delete("Anh đã ổn hơn", false);

	}

}
