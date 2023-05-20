package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SongModel {
    private int songId;
    private String songName;
    private String artistName;
    private String albumName;
    private String musicFileUrl;
    private String backgroundImageFileUrl;

}
