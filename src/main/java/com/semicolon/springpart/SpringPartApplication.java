package com.semicolon.springpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;



@SpringBootApplication
@EnableCaching
public class SpringPartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPartApplication.class, args);
	}

}
