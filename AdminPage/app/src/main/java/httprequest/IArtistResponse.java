package httprequest;

import models.ArtistModel;

public interface IArtistResponse extends IHttpResponse<ArtistModel> {
    ArtistModel create(ArtistModel data) throws Exception;
}
