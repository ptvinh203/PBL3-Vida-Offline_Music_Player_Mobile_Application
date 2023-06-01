package httprequest.implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import httprequest.ISongResponse;
import httprequest.config.Config_URL;
import models.SongModel;

public class SongResponseImpl implements ISongResponse {
    private final String URL_STR = Config_URL.SERVER_IP + "/songs";
    private HttpURLConnection connection;

    public static SongModel parseSongModel(JSONObject jsonObject) throws JSONException {
        SongModel result = new SongModel();
        result.setSongId(jsonObject.getInt("songId"));
        result.setSongName(jsonObject.getString("songName"));
        result.setArtistName(jsonObject.getString("artistName"));
        if (!jsonObject.isNull("albumName")) {
            result.setAlbumName(jsonObject.getString("albumName"));
        }

        if (!jsonObject.isNull("musicFileUrl")) {
            result.setMusicFileUrl(jsonObject.getString("musicFileUrl"));
        }
        if (!jsonObject.isNull("backgroundImageFileUrl")) {
            result.setBackgroundImageFileUrl(jsonObject.getString("backgroundImageFileUrl"));
        }

        return result;
    }

    @Override
    public SongModel findById(int id) throws Exception {
        URL url = new URL(URL_STR + "/" + id);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(2000);
        connection.setReadTimeout(2000);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            connection.disconnect();
            JSONObject jsonObject = new JSONObject(response.toString());
            return parseSongModel(jsonObject);
        } else {
            throw new Exception("SONG: Can't get data from server!");
        }
    }

    @Override
    public List<SongModel> findAll() throws Exception {
        URL url = new URL(URL_STR + "/all");

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(2000);
        connection.setConnectTimeout(2000);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            connection.disconnect();
            List<SongModel> result = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                result.add(parseSongModel(jsonArray.getJSONObject(i)));
            }
            return result;
        } else {
            throw new Exception("SONG: Can't get data from server!");
        }
    }

    @Override
    public SongModel create(SongModel data, Integer id, boolean isArtist) throws Exception {
        URL url = (isArtist) ? new URL(URL_STR + "/artist/" + id) : new URL(URL_STR + "/album/" + id);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setReadTimeout(2000);
        connection.setConnectTimeout(2000);

        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        String jsonString = new Gson().toJson(data);
        outputStream.write(jsonString.getBytes(Charset.forName("UTF-8")));
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            connection.disconnect();
        } else {
            throw new Exception("SONG: Server returned non-OK status: " + responseCode);
        }
        return parseSongModel(new JSONObject(response.toString()));
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        URL url = new URL(URL_STR + "/" + id);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setReadTimeout(2000);
        connection.setConnectTimeout(2000);

        StringBuffer response = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();
        connection.disconnect();
    }

    @Override
    public boolean update(int id, SongModel data) throws Exception {
        URL url = new URL(URL_STR + "/" + id);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setReadTimeout(2000);
        connection.setConnectTimeout(2000);

        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        String jsonString = new Gson().toJson(data);
        outputStream.write(jsonString.getBytes(Charset.forName("UTF-8")));
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
            return true;
        }
        return false;
    }

    @Override
    public List<SongModel> search(String prefix) throws Exception {
        String URL_CONNECTION;
        if (prefix == null || prefix.isEmpty()) {
            URL_CONNECTION = URL_STR + "/all";
        } else {
            StringBuffer prefixBuffer = new StringBuffer(prefix);

            URL_CONNECTION = (Config_URL.SERVER_IP + "/search/song/"
                    + URLEncoder.encode(prefixBuffer.toString(), "UTF-8")).replaceAll("\\+", "%20");
        }
        URL url = new URL(URL_CONNECTION);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setReadTimeout(2000);
        connection.setConnectTimeout(2000);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            connection.disconnect();
            List<SongModel> result = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                result.add(parseSongModel(jsonArray.getJSONObject(i)));
            }
            return result;
        } else {
            throw new Exception("SONG-SEARCH: Can't get data from server!\nStatus code: "
                    + responseCode);
        }
    }
}
