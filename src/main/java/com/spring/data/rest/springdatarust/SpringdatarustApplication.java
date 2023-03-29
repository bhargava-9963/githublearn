package com.spring.data.rest.springdatarust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;

@SpringBootApplication
public class SpringdatarustApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringdatarustApplication.class, args);
	}
}
