package com.example.eureka.address.controller;

import java.util.ArrayList;
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
public class AddressRestController {

	@Autowired
	private EurekaClient	eurekaClient;

	@Autowired
	private RestTemplate	restTemplate;

	private final String	NAME_SVC	= "NAME-SVC";

	@RequestMapping("/service-instances/{applicationName}")
	public Applications serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.eurekaClient.getApplications(applicationName);
	}

	@HystrixCommand(fallbackMethod = "getNamesBackup")
	@RequestMapping("/addresses")
	public List<String> getAddresses(@RequestParam(required = false,
			defaultValue = "0") int nameId) {

		ResponseEntity<List<String>> nameResponse = restTemplate.exchange("http://" + NAME_SVC + "/names/" + nameId,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<String>>() {});

		List<String> addresses = new ArrayList<>();

		addresses.add("21st Jump Street");
		addresses.add("Beverly Hills");

		List<String> combined = nameResponse.getBody()
											.stream()
											.map(name -> name + " lives in " + addresses.get(nameResponse	.getBody()
																											.indexOf(
																													name)))
											.collect(Collectors.toList());

		return combined;
	}

	public List<String> getNamesBackup(@RequestParam(required = false,
			defaultValue = "0") int nameId) {
		System.out.println("Fallback operation");

		List<String> nameAddresses = new ArrayList<>();

		nameAddresses.add("Nobody lives here at the moment");

		return nameAddresses;
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
