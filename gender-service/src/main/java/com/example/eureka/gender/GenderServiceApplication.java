package com.example.eureka.gender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCircuitBreaker
@SpringBootApplication
@EnableEurekaClient
public class GenderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenderServiceApplication.class, args);
	}
}
