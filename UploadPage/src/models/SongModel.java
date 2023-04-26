package models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor@NoArgsConstructor
@Setter@Getter
@Builder
public class SongModel {
    private int songId;
    private String songName;
    private String musicFileUrl;
    private String backgroundImageFileUrl;
}
