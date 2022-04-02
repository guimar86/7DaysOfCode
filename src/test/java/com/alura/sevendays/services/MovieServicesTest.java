package com.alura.sevendays.services;

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
}
