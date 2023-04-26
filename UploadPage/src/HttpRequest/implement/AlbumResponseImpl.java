package HttpRequest.implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import HttpRequest.IAlbumResponse;
import models.AlbumModel;
import models.SongModel;

public class AlbumResponseImpl implements IAlbumResponse {
    private final String URL_STR = "http://localhost:8080/albums";
    private HttpURLConnection connection;
    
    public static AlbumModel parseAlbumModel(JSONObject jsonObject) {
        AlbumModel result = new AlbumModel();
        result.setAlbumId(jsonObject.getInt("albumId"));
        result.setAlbumName(jsonObject.getString("albumName"));

        if (!jsonObject.isNull("songsAlbum")) {
            List<SongModel> listSongModels = new ArrayList<>();
            JSONArray jsonSongArray = jsonObject.getJSONArray("songsAlbum");
            for (int i = 0; i < jsonSongArray.length(); i++) {
                listSongModels.add(SongResponseImpl.parseSongModel(jsonSongArray.getJSONObject(i)));
            }
        }
        return result;
    }

    @Override
    public AlbumModel findById(int id) throws Exception {
        URL url = new URL(URL_STR + "/" + id);

        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            connection.disconnect();
            JSONObject jsonObject = new JSONObject(response.toString());
            return parseAlbumModel(jsonObject);
        }
        else {
            throw new Exception("Can't get data from server!");
        }
    }

    @Override
    public List<AlbumModel> findAll() throws Exception {
        URL url = new URL(URL_STR + "/all");

        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = reader.readLine()) != null) {
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
        }
        else {
            throw new Exception("Can't get data from server!");
        }
    }

    @Override
    public AlbumModel create(AlbumModel data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public List<SongModel> findAllSongByAlbumId(int albumId) throws Exception {
        URL url = new URL(URL_STR + "/" + albumId + "/all-songs");

        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while((inputLine = reader.readLine()) != null) {
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
        }
        else {
            throw new Exception("Can't get data from server!");
        }
    }

    @Override
    public String update(int id, AlbumModel data) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


}
