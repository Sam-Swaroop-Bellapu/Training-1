package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}


/**
 * PATCH with example
 * Enhance existing employee with Salary
 * Query parameters - https://www.baeldung.com/spring-request-param
 * Explore Header params - https://www.baeldung.com/spring-rest-http-headers
 */