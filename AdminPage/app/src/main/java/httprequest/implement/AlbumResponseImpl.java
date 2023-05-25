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

import httprequest.IAlbumResponse;
import httprequest.config.Config_URL;
import models.AlbumModel;
import models.SongModel;

public class AlbumResponseImpl implements IAlbumResponse {
    private final String URL_STR = Config_URL.SERVER_IP + "/albums";
    private HttpURLConnection connection;

    public static AlbumModel parseAlbumModel(JSONObject jsonObject) throws JSONException {
        AlbumModel result = new AlbumModel();
        result.setAlbumId(jsonObject.getInt("albumId"));
        result.setAlbumName(jsonObject.getString("albumName"));

        List<SongModel> listSongModels = new ArrayList<>();
        if (!jsonObject.isNull("songsAlbum")) {
            JSONArray jsonSongArray = jsonObject.getJSONArray("songsAlbum");
            for (int i = 0; i < jsonSongArray.length(); i++) {
                listSongModels.add(SongResponseImpl.parseSongModel(jsonSongArray.getJSONObject(i)));
            }
        }
        result.setSongsAlbum(listSongModels);

        return result;
    }

    @Override
    public AlbumModel findById(int id) throws Exception {
        URL url = new URL(URL_STR + "/" + id);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);
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
            return parseAlbumModel(jsonObject);
        } else {
            throw new Exception("ALBUM: Can't get data from server!");
        }
    }

    @Override
    public List<AlbumModel> findAll() throws Exception {
        URL url = new URL(URL_STR + "/all");

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);

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
            List<AlbumModel> result = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                result.add(parseAlbumModel(jsonArray.getJSONObject(i)));
            }
            return result;
        } else {
            throw new Exception("ALBUM: Can't get data from server!");
        }
    }

    @Override
    public AlbumModel create(AlbumModel data, Integer artistId) throws Exception {
        URL url = new URL(URL_STR + "/" + artistId);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-type",
                "application/json;charset=UTF-8");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

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
            throw new Exception("ALBUM: Server returned non-OK status: " + responseCode);
        }
        return parseAlbumModel(new JSONObject(response.toString()));
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        URL url = new URL(URL_STR + "/" + id);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setConnectTimeout(5000);

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
    public List<SongModel> findAllSongByAlbumId(int albumId) throws Exception {
        URL url = new URL(URL_STR + "/" + albumId + "/all-songs");

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);
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
                result.add(SongResponseImpl.parseSongModel(jsonArray.getJSONObject(i)));
            }
            return result;
        } else {
            throw new Exception("ALBUM: Can't get data from server!");
        }
    }

    @Override
    public boolean update(int id, AlbumModel data) throws Exception {
        URL url = new URL(URL_STR + "/" + id);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);

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
    public List<AlbumModel> search(String prefix) throws Exception {
        String URL_CONNECTION;
        if (prefix == null || prefix.isEmpty()) {
            URL_CONNECTION = URL_STR + "/all";
        } else {
            StringBuffer prefixBuffer = new StringBuffer(prefix);

            URL_CONNECTION = (Config_URL.SERVER_IP + "/search/album/"
                    + URLEncoder.encode(prefixBuffer.toString(), "UTF-8")).replaceAll("\\+", "%20");
        }
        URL url = new URL(URL_CONNECTION);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);

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
            List<AlbumModel> result = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                result.add(parseAlbumModel(jsonArray.getJSONObject(i)));
            }
            return result;
        } else {
            throw new Exception("ALBUM-SEARCH: Can't get data from server!\nStatus code: "
                    + responseCode);
        }
    }

}
