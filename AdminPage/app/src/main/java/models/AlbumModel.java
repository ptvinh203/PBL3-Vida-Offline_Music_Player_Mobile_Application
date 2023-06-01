package models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AlbumModel {
    private int albumId;
    private String albumName;
    private String artistName;
    private List<SongModel> songsAlbum;
}
