package com.pbl3.musicapplication;

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

	

	// @Autowired
	// private AlbumRepository	albumRepository;

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

	// @Test
	// void test2() {
	// 	Artist artist = artistRepository.findById(129).orElse(null);

	// 	Album album = albumRepository.findById(353).orElse(null);

	// 	List<Album> lAlbums = artist.getAlbums();
	// 	System.out.println(lAlbums.get(0).getAlbumName());
	// }

}
