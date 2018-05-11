package com.example.zuul.apiproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ZuulApiproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiproxyApplication.class, args);
	}
}
