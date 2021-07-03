package com.sam.proj.findlatlong.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sam.proj.findlatlong.service.LatLongService;

import org.springframework.stereotype.Service;

@Service
public class LatLongServiceImpl implements LatLongService {

    public String getLatitudeLongitude(String url) throws IOException {
        URL urlObject = new URL(url);
        HttpURLConnection connection = getConnection(urlObject);
        return getResponseBody(connection);
    }
 
    private String getResponseBody(HttpURLConnection connection) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
        }
        return sb.toString();
    }

    private HttpURLConnection getConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection;
    }
   
}