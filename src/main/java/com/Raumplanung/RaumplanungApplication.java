package com.Raumplanung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RaumplanungApplication {
	private final static int PORT = 8080;

	public static void main(String[] args) {
		SpringApplication.run(RaumplanungApplication.class, args);
	}

}
