package httprequest.implement;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.UUID;

import httprequest.IMyFileResponse;
import httprequest.config.Config_URL;

public class MyFileResponseImpl implements IMyFileResponse {
    private final String URL_STR = Config_URL.SERVER_IP + "/file";
    private HttpURLConnection connection;

    @Override
    public String artistFileUpload(int artistId, File artistFile) throws Exception {
        URL url = new URL(URL_STR + "/uploadFile/artist/" + artistId);

        String boundary = UUID.randomUUID().toString();
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);

        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, Charset.forName("UTF-8")), true);
        writer.append("--" + boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + artistFile.getName() + "\"\r\n");
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(artistFile.getName()) + "\r\n");
        writer.append("Content-Transfer-Encoding: binary\r\n");
        writer.append("\r\n");
        writer.flush();

        FileInputStream fileInputStream = new FileInputStream(artistFile);
        byte[] buffer = new byte[1024];
        int bufferedReader = -1;
        while ((bufferedReader = fileInputStream.read(buffer)) != -1) {
            os.write(buffer, 0, bufferedReader);
        }
        os.flush();
        fileInputStream.close();
        writer.append("\r\n");
        writer.flush();

        writer.flush();
        writer.append("--" + boundary + "--\r\n");
        writer.close();

        int responseCode = connection.getResponseCode();
        String response = "";
        if (responseCode == HttpURLConnection.HTTP_OK) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] out_buffer = new byte[1024];
            int length;
            while ((length = connection.getInputStream().read(out_buffer)) != -1) {
                byteArrayOutputStream.write(out_buffer, 0, length);
            }
            response = byteArrayOutputStream.toString(Charset.forName("UTF-8"));
            connection.disconnect();
        } else
            throw new Exception("FILE: Server returned non-OK status: " + responseCode);

        return response;
    }

    @Override
    public String songFileUpload(int songId, File songFile) throws Exception {
        URL url = new URL(URL_STR + "/uploadFile/song/" + songId);

        String boundary = UUID.randomUUID().toString();
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setReadTimeout(5000);
        connection.setConnectTimeout(5000);

        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, Charset.forName("UTF-8")), true);
        writer.append("--" + boundary).append("\r\n");
        writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + songFile.getName() + "\"\r\n");
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(songFile.getName()) + "\r\n");
        writer.append("Content-Transfer-Encoding: binary\r\n");
        writer.append("\r\n");
        writer.flush();

        FileInputStream fileInputStream = new FileInputStream(songFile);
        byte[] buffer = new byte[1024];
        int bufferedReader = -1;
        while ((bufferedReader = fileInputStream.read(buffer)) != -1) {
            os.write(buffer, 0, bufferedReader);
        }
        os.flush();
        fileInputStream.close();
        writer.append("\r\n");
        writer.flush();

        writer.flush();
        writer.append("--" + boundary + "--\r\n");
        writer.close();

        int responseCode = connection.getResponseCode();
        String response = "";
        if (responseCode == HttpURLConnection.HTTP_OK) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] out_buffer = new byte[1024];
            int length;
            while ((length = connection.getInputStream().read(out_buffer)) != -1) {
                byteArrayOutputStream.write(out_buffer, 0, length);
            }
            response = byteArrayOutputStream.toString(Charset.forName("UTF-8"));
            connection.disconnect();
        } else
            throw new Exception("FILE: Server returned non-OK status: " + responseCode);

        return response;
    }

    @Override
    public void deleteById(int id) throws Exception {
        URL url = new URL(URL_STR + "/" + id);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setReadTimeout(5000);
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

}
