package com.alura.sevendays.services;

import com.alura.sevendays.SevendaysApplication;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Component
public class MoviesService {

    Logger logger = LoggerFactory.getLogger(MoviesService.class);

    @Value("${imdb.url}")
    public String imdb_url;

    @Value("${imdb.api.key}")
    public String imdb_api_key;



    /***
     * Get top 250 movies but arranged as an object.
     * @return
     */
    public Top250Data GetTop250MoviesToObject(){

        String api_url = imdb_url + "/" + imdb_api_key;

        HttpResponse webCallResponse=MakeACall(api_url);

        ObjectMapper mapper=new ObjectMapper();
        Top250Data top250Data= null;
        try {
            top250Data = mapper.readValue(webCallResponse.body().toString(), Top250Data.class);

            if(top250Data!=null){

                logger.info("List of Titles");
                top250Data.getItems().stream().filter(p->p.getTitle()!=null).forEach(System.out::println);
                logger.info("List of Images urls");
                top250Data.getItems().stream().filter(p->p.getImage()!=null).forEach(System.out::println);
            }

        } catch (JsonProcessingException | NullPointerException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return top250Data;

    }
    private HttpResponse MakeACall(String url) {

        HttpClient client = HttpClient.newBuilder().build();

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
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
