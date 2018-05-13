package com.example.eureka.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientRestController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@RequestMapping("/names/{nameId}")
	public List<String> getNames(@PathVariable("nameId") int nameId) {
		List<String> names = new ArrayList<>();

		if (nameId == 1) {
			names.add("Henry");
			names.add("Thomas");
		} else {
			names.add("Sabrina");
			names.add("Felicia");
		}

		return names;
	}
}
