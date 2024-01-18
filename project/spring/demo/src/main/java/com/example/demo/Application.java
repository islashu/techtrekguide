package com.example.demo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Contains components like @EnableAutoConfiguration, @ComponentScan, and @Configuration
// @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
// @ComponentScan: Tells Spring to look for other components, configurations, and services, such as @RestController and @Service in the same directory onwards so it can find all the locations of these services without us having to specify each one.
// @Configuration: Tags the class as a source of bean definitions for the application context.
// @SpringBootApplication: Does all the necessary configuration and integration of the different packages and beans from each dependency so that the application can work without us doing anything else, we will need to configure all the paths and other things that we need for the application to work.
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		// Checks connections with MongoDB
//		MongoClient client = MongoClients.create("mongodb://localhost:27017/");
//		client.listDatabases().forEach(db -> System.out.println(db));
//		client.getDatabase("steamdb").listCollectionNames().forEach(collection -> System.out.println(collection));
		System.out.println("HELLO World");
	}
}
