package httprequest;

import java.util.List;

import models.ArtistModel;

public interface IArtistResponse extends IHttpResponse<ArtistModel> {
    ArtistModel create(ArtistModel data) throws Exception;

    List<ArtistModel> search(String prefix) throws Exception;
}
