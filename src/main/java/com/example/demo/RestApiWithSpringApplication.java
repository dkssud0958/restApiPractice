package com.example.demo;

import org.modelmapper.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

@SpringBootApplication
public class RestApiWithSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiWithSpringApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
