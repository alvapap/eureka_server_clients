package com.example.eureka.second.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaSecondClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaSecondClientApplication.class, args);
    }
}
