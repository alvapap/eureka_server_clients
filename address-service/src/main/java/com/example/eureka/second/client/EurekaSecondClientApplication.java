package com.example.eureka.second.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCircuitBreaker
@SpringBootApplication
@EnableEurekaClient
public class EurekaSecondClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaSecondClientApplication.class, args);
	}
}
