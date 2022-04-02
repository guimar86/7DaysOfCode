package com.alura.sevendays.services;

import com.alura.sevendays.models.Top250Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MovieServicesTest {

    @Autowired
    private MoviesService service;

    @Test
    public void TestGetTop250Movies(){

        Map<Integer, Object> map=service.GetTop250Movies();

        assertThat(map.containsKey(200)).isTrue();

    }

    @Test
    public void TestGetTop250MoviesToObject(){

        Top250Data top250Data=service.GetTop250MoviesToObject();

        assertThat(top250Data).isNotNull();
        assertThat(top250Data.ErrorMessage).isEmpty();

    }
}
