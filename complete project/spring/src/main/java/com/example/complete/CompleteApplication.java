package com.example.complete;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompleteApplication {
    /*
     * TODO:
     * Try catch and passing errors to the frontend
     * Logging
     * Custom Querying
     * */


    @Value("${server.port}")
    private String PORT;

    public static void main(String[] args) {
        SpringApplication.run(CompleteApplication.class, args);
    }


    // If you want to use @Value annotation in a class that is not a bean, you can use the following:
    @PostConstruct
    public void init() {
        System.out.println("Spring Boot Application Started");
        System.out.println("Server running at " + "http://localhost:" + PORT);
    }

}
