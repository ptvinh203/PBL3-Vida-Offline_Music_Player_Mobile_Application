package httprequest.implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import httprequest.IUserResponse;
import httprequest.config.Config_URL;
import models.UserModel;

public class UserResponseImpl implements IUserResponse {
    private final String URL_STR = Config_URL.SERVER_IP + "/users";
    private HttpURLConnection connection;

    public static UserModel parseUserModel(JSONObject jsonObject) throws JSONException {
        UserModel result = new UserModel();
        result.setUserId(jsonObject.getInt("userId"));
        result.setUsername(jsonObject.getString("username"));
        result.setFullName(jsonObject.getString("fullName"));
        result.setPhoneNumber(jsonObject.getString("phoneNumber"));
        return result;
    }

    @Override
    public void deleteById(Integer userId) throws Exception {
        URL url = new URL(URL_STR + "/" + userId);

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
    public List<UserModel> findAll() throws Exception {
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
            List<UserModel> result = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                result.add(parseUserModel(jsonArray.getJSONObject(i)));
            }
            return result;
        } else {
            throw new Exception("USER: Can't get data from server!");
        }
    }

}
