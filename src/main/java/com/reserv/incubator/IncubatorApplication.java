package com.reserv.incubator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IncubatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncubatorApplication.class, args);
	}
}
