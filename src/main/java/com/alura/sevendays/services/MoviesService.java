package com.alura.sevendays.services;

import com.alura.sevendays.SevendaysApplication;
import com.alura.sevendays.models.Movie;
import com.alura.sevendays.models.Top250Data;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Component
public class MoviesService {

    @Value("${imdb.url}")
    public String imdb_url;
    @Value("${imdb.api.key}")
    public String imdb_api_key;
    Logger logger = LoggerFactory.getLogger(MoviesService.class);

    public List<Movie> ListTop250Movies() throws JsonProcessingException {

        String api_url = imdb_url + "/" + imdb_api_key;

        HttpResponse webCallResponse = MakeACall(api_url);

        ObjectMapper mapper = new ObjectMapper();
        Top250Data top250Data = null;
        List<Movie> movies = new ArrayList<>();
        top250Data = mapper.readValue(webCallResponse.body().toString(), Top250Data.class);
        movies = top250Data.getItems().stream().map(p -> {

            Movie movie = new Movie();
            movie.setRating(p.getIMDbRating());
            movie.setTitle(p.getTitle());
            movie.setUrlImage(p.getImage());
            movie.setYear(p.getYear());
            return movie;
        }).collect(Collectors.toList());


        return movies;
    }

    /***
     * Get top 250 movies but arranged as an object.
     * @return
     */
    public Top250Data GetTop250MoviesToObject() {

        String api_url = imdb_url + "/" + imdb_api_key;

        HttpResponse webCallResponse = MakeACall(api_url);

        ObjectMapper mapper = new ObjectMapper();
        Top250Data top250Data = null;
        try {
            top250Data = mapper.readValue(webCallResponse.body().toString(), Top250Data.class);

            if (top250Data != null) {
                logger.info("List of Titles");
                top250Data.getItems().forEach(p -> logger.info(p.getTitle()));
                logger.info("List of Images urls");
                top250Data.getItems().forEach(p -> logger.info(p.getImage()));
            }

        } catch (JsonProcessingException | NullPointerException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return top250Data;

    }

    /***
     * Private method used to make HTTP Calls
     * @param url
     * @return
     */
    private HttpResponse MakeACall(String url) {

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.error(response.body().toString());
                logger.info(response.headers().toString());
            }
            //logger.info("HTTP Response: " + response.body().toString());
            return response;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    /**
     * GetTop250Movies
     * Service used to get a list of top 250 movies from IMDB
     *
     * @return
     */
    public Map<Integer, Object> GetTop250Movies() {

        String api_url = imdb_url + "/" + imdb_api_key;

        HttpClient client = HttpClient.newBuilder().build();
        try {
            var request = HttpRequest.newBuilder()
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
