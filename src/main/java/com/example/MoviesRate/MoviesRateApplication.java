package com.example.MoviesRate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class MoviesRateApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MoviesRateApplication.class, args);
		int port = Integer.parseInt(System.getenv("PORT"));
		SpringApplication app = new SpringApplication(MoviesRateApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", String.valueOf(port)));
		app.run(args);
	}

}
