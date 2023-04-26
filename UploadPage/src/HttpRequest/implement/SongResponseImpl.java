package HttpRequest.implement;

import org.json.JSONObject;

import models.SongModel;

public class SongResponseImpl {
    
    public static SongModel parseSongModel(JSONObject jsonObject) {
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
}
