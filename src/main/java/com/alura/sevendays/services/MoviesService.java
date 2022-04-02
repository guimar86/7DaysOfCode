package com.alura.sevendays.services;

import com.alura.sevendays.SevendaysApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@Component
public class MoviesService {

    Logger logger = LoggerFactory.getLogger(MoviesService.class);

    @Value("${imdb.url}")
    public String imdb_url;

    @Value("${imdb.api.key}")
    public String imdb_api_key;

    public Map<Integer, Object> GetTop250Movies() {

        String api_url = imdb_url + "/" + imdb_api_key;



        HttpClient client = HttpClient.newBuilder().build();
        try {
            var request =  HttpRequest.newBuilder()
                    .uri(new URI(api_url))
                    .GET()
                    .build();

            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Map<Integer, Object> map = new HashMap<>();
            map.put(response.statusCode(), response.body());
            logger.info(response.body().toString());
            return map;

        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

}
