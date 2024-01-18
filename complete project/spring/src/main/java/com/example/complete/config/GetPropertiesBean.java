package com.example.complete.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GetPropertiesBean {

    private String PORT;

//    @Autowired
//    public GetPropertiesBean(@Value("${server.port}") String PORT) {
//        System.out.println("GetPropertiesBean constructor called");
//        System.out.println("PORT: " + PORT);
//    }

}
