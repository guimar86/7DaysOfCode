package com.alura.sevendays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@SpringBootApplication
public class SevendaysApplication {



    public static void main(String[] args) {

        SpringApplication.run(SevendaysApplication.class, args);

    }

}
