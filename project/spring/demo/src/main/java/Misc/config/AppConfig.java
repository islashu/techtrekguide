package Misc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// @Configuration allows Spring to pick up during the enable configuration scanning phase.
@Configuration
public class AppConfig {
  /*
   * Use the standard Mongo driver API to create a com.mongodb.client.MongoClient instance.
   */
   public @Bean com.mongodb.client.MongoClient mongoClient() {
       System.out.println("Creating the mongo client");
       return com.mongodb.client.MongoClients.create("mongodb://localhost:27017/steamdb");
   }
}
