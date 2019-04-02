package com.wonder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuApplication.class, args);
	}
}
