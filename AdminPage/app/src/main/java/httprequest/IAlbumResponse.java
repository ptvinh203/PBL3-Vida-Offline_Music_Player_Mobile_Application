package httprequest;

import java.util.List;

import models.AlbumModel;
import models.SongModel;

public interface IAlbumResponse extends IHttpResponse<AlbumModel> {
    List<SongModel> findAllSongByAlbumId(int albumId) throws Exception;
}
