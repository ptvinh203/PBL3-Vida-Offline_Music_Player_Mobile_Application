package httprequest;

import java.util.List;

import models.AlbumModel;
import models.SongModel;

public interface IAlbumResponse extends IHttpResponse<AlbumModel> {
    AlbumModel create(AlbumModel data, Integer artistId) throws Exception;

    List<SongModel> findAllSongByAlbumId(int albumId) throws Exception;

    List<AlbumModel> search(String prefix) throws Exception;
}
