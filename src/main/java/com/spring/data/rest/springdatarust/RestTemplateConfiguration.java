package com.spring.data.rest.springdatarust;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplateBuilder()
                .rootUri("http://localhost:8080")
                .setReadTimeout(Duration.ofNanos(0))
                .build();
    }
}
