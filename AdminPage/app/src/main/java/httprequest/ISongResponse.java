package httprequest;

import java.util.List;

import models.SongModel;

public interface ISongResponse extends IHttpResponse<SongModel> {
    SongModel create(SongModel data, Integer id, boolean isArtist) throws Exception;

    List<SongModel> search(String prefix) throws Exception;
}
