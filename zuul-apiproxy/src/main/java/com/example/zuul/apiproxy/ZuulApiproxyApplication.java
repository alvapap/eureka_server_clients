package com.example.zuul.apiproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.zuul.apiproxy.filters.ProxyFilter;
import com.example.zuul.apiproxy.filters.StartPreFilter;
import com.example.zuul.apiproxy.filters.StopPostFilter;

@EnableZuulProxy
@SpringBootApplication
public class ZuulApiproxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiproxyApplication.class, args);
	}
	
	@Bean
	public ProxyFilter proxyFilter() {
		return new ProxyFilter();
	}
	
	@Bean
	public StartPreFilter startPreFilter() {
		return new StartPreFilter();
	}
	
	@Bean
	public StopPostFilter stopPostFilter() {
		return new StopPostFilter();
	}
}
