package httprequest.implement;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import httprequest.ISongResponse;
import models.SongModel;

public class SongResponseImpl implements ISongResponse {

    public static SongModel parseSongModel(JSONObject jsonObject) throws JSONException {
        SongModel result = new SongModel();
        result.setSongId(jsonObject.getInt("songId"));
        result.setSongName(jsonObject.getString("songName"));

        if (!jsonObject.isNull("musicFileUrl")) {
            result.setMusicFileUrl(jsonObject.getString("musicFileUrl"));
        }
        if (!jsonObject.isNull("backgroundImageFileUrl")) {
            result.setBackgroundImageFileUrl("backgroundImageFileUrl");
        }
        return result;
    }

    @Override
    public SongModel findById(int id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<SongModel> findAll() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public SongModel create(SongModel data) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public String update(int id, SongModel data) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
