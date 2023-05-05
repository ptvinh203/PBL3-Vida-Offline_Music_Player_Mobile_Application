package HttpRequest.implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import HttpRequest.IArtistResponse;
import models.AlbumModel;
import models.ArtistModel;
import models.SongModel;

public class ArtistResponseImpl implements IArtistResponse {
    private final String URL_STR = "http://localhost:8080/artists";
    private HttpURLConnection connection;

    public static ArtistModel parseArtistModel(JSONObject jsonObject) throws JSONException {
        ArtistModel result = new ArtistModel();
        result.setArtistId(jsonObject.getInt("artistId"));
        result.setArtistName(jsonObject.getString("artistName"));

        if (!jsonObject.isNull("artistImageUrl")) {
            result.setArtistImageUrl(jsonObject.getString("artistImageUrl"));
        }

        if (!jsonObject.isNull("albums")) {
            List<AlbumModel> listAlbumModels = new ArrayList<>();
            JSONArray jsonAlbumArray = jsonObject.getJSONArray("albums");
            for (int i = 0; i < jsonAlbumArray.length(); i++) {
                listAlbumModels.add(AlbumResponseImpl.parseAlbumModel(jsonAlbumArray.getJSONObject(i)));
            }
            result.setAlbums(listAlbumModels);
        }
        if (!jsonObject.isNull("singleAndEpSongs")) {
            List<SongModel> listSongModels = new ArrayList<>();
            JSONArray jsonSingleSongArray = jsonObject.getJSONArray("singleAndEpSongs");
            for (int i = 0; i < jsonSingleSongArray.length(); i++) {
                listSongModels.add(SongResponseImpl.parseSongModel(jsonSingleSongArray.getJSONObject(i)));
            }
            result.setSingleAndEpSongs(listSongModels);
        }
        return result;
    }
    public static String jsonStringArtistModel(ArtistModel artistModel) {


        StringBuffer jsonString = new StringBuffer();
        jsonString.append("{\"artistName\":" + "\"" + artistModel.getArtistName() + "\"");

        return jsonString.toString();
    }
    @Override
    public ArtistModel findById(int id) throws Exception {
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
            return parseArtistModel(jsonObject);
        }
        else {
            throw new Exception("Can't get data from server!");
        }
    }

    @Override
    public List<ArtistModel> findAll() throws Exception{
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
            List<ArtistModel> result = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                result.add(parseArtistModel(jsonArray.getJSONObject(i)));
            }
            return result;
        }
        else {
            throw new Exception("Can't get data from server!");
        }
    }

    @Override
    public ArtistModel create(ArtistModel data) throws Exception {
        URL url = new URL(URL_STR);

        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
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
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            connection.disconnect();
        }
        else {
            throw new Exception("Server returned non-OK status: " + responseCode);
        }
        return parseArtistModel(new JSONObject(response.toString()));
    }

    @Override
    public void deleteById(Integer id) throws Exception{
        URL url = new URL(URL_STR + "/" + id);

        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);


        StringBuffer response = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        while((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();
        connection.disconnect();
    }
    @Override
    public String update(int id, ArtistModel data) throws Exception {
        URL url = new URL(URL_STR + "/" + id);

        connection = (HttpURLConnection)url.openConnection();
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
            return "Updated";
        }
        return "Can't update";
    }



}
