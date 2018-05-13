package com.example.eureka.gender.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RibbonClient(name = "NAME-SVC")
@RestController
public class EurekaGenderController {

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplate restTemplate;

	private final String NAME_SVC = "NAME-SVC";

	@RequestMapping("/service-instances/{applicationName}")
	public Applications serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.eurekaClient.getApplications(applicationName);
	}

	@HystrixCommand(fallbackMethod = "getGenderBackup")
	@RequestMapping(path = "/gender", params = { "nameId" })
	public String getGender(@RequestParam int nameId) {

		ResponseEntity<List<String>> nameResponse = restTemplate.exchange("http://" + NAME_SVC + "/names/" + nameId,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});

		String concatenatedNames = nameResponse.getBody().stream().map(Object::toString).collect(Collectors.joining(", "));
		String result;

		if (nameId == 1) {
			result = concatenatedNames + " are men";
		} else {
			result = concatenatedNames + " are women";
		}

		return result;
	}

	public String getGenderBackup(@RequestParam int nameId) {
		System.out.println("Fallback operation");

		String result = "We are gender fluid";

		return result;
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
