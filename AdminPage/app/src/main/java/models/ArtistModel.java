package models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ArtistModel {
    private int artistId;
    private String artistName;
    private String artistImageUrl;
    private List<AlbumModel> albums;
    private List<SongModel> singleAndEpSongs;
}
