package com.pbl3.musicapplication;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pbl3.musicapplication.model.entity.Artist;
import com.pbl3.musicapplication.model.entity.Song;
import com.pbl3.musicapplication.model.repository.ArtistRepository;
import com.pbl3.musicapplication.model.repository.SongRepository;

@SpringBootTest
class MusicapplicationApplicationTests {

	@Autowired
	private SongRepository songRepository;
	@Autowired
	private ArtistRepository artistRepository;

	

	@Test
	void contextLoads() {
	}

	@Test
	void test1() {
		Artist artist = artistRepository.findById(129).orElse(null);
		
		Song song = songRepository.findById(193).orElse(null);
		System.out.println(song.getSongName());
		List<Song> lSong = artist.getSingleAndEpSongs();

		System.out.println(song + "\n" + lSong.get(4));

		System.out.println(lSong.indexOf(song));
		for (Song x : lSong) {
			System.out.println("--------------------------" + x.getSongName());
		}
		System.out.println("------------------AFTER");


		for (Song x : lSong) {
			System.out.println("--------------------------" + x.getSongName());
		}
		artist.setSingleAndEpSongs(lSong);
		artistRepository.save(artist);

		System.out.println("-----------------Finish!");
		
	}

	@Test
	public void test2() {
		String ARTIST_FILE_URL = "/src/main/java/com/pbl3/musicapplication/algorithm/storage/ArtistTrieStorage.txt";
		File file = new File(ARTIST_FILE_URL);
		System.out.println(file.getParent());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.isFile());

		// Trie trie = new Trie();
        // trie.insert("apple");
        // trie.insert("banana");
        // trie.insert("cat");
        // trie.insert("caterpillar");
        // trie.insert("call");

        // List<String> completions = trie.autocomplete("ca");
        // for (String completion : completions) {
        //     System.out.println(completion);
        // }
	}

}
