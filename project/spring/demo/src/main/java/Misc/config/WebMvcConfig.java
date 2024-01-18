package Misc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// part of the spring MVC framework, not part of the spring security framework, so if you do not
// have spring security in your project, you can use this class to enable CORS.

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        WebMvcConfigurer.super.addCorsMappings(registry);
//        registry
//                .addMapping("/coffee")
//                .allowedOrigins("http://localhost:3000");
//    }
}
