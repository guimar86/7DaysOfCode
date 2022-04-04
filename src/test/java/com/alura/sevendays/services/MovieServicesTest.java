package com.alura.sevendays.services;

import com.alura.sevendays.models.Movie;
import com.alura.sevendays.models.Top250Data;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class MovieServicesTest {

    @Autowired
    private MoviesService service;

    /**
     * Day one Test
     */
    @Test
    public void TestGetTop250Movies() {

        Map<Integer, Object> map = service.GetTop250Movies();
        assertThat(map.containsKey(200)).isTrue();
    }

    /**
     * Day two test
     */
    @Test
    public void TestGetTop250MoviesToObject() {

        Top250Data top250Data = service.GetTop250MoviesToObject();
        assertThat(top250Data).isNotNull();
        assertThat(top250Data.ErrorMessage).isEmpty();

    }

    /***
     * Day 3 test
     * @throws JsonProcessingException
     */
    @Test
    public void ListTop250MoviesTest() throws JsonProcessingException {

        List<Movie> movies=service.ListTop250Movies();

        assertThat(movies).isNotNull();
        assertThat(movies).isNotEmpty();


    }
}
